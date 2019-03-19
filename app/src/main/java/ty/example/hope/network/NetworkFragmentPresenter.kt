package ty.example.hope.network

import android.util.Log
import ty.example.hope.network.okhttp2.Okhttp2Factory
import ty.example.hope.network.okhttp3.Okhttp3Factory
import ty.example.hope.network.okhttp3.Okhttp3NetworkGet
import ty.example.hope.network.urlconnection.UrlConnectionFactory
import ty.example.hope.util.ConstConfigure

/**
 * @description: NetworkFragmentPresenter
 **
 * @author: hope
 **
 * @create: 2019-03-04 15:45
 */
class NetworkFragmentPresenter constructor(var networkFragmentView: NetworkFragmentContract.View):
        NetworkFragmentContract.Presenter{

    private lateinit var networkFactory: NetworkAbstractFactory
    init {
        networkFragmentView.presenter = this
    }

    override fun start() {
        networkFragmentView.initView()
    }

    override fun doNetwork() {
        Log.i(ConstConfigure.TAG, "httplibtype" + networkFragmentView.getHttpLibType() +
                ", enum value:" + HttpLibType.OKHTTP3.toString())

        if(networkFragmentView.getHttpLibType().equals(HttpLibType.OKHTTP3.toString())) {
            networkFactory = Okhttp3Factory()
            for(i in 1..10) {
                getNetwork().doNetworkAsync()
            }
        }else if(networkFragmentView.getHttpLibType().equals(HttpLibType.OKHTTP2.toString())){
            networkFactory = Okhttp2Factory()
            getNetwork().doNetworkAsync()
        }else if(networkFragmentView.getHttpLibType().equals(HttpLibType.URLConnection.toString())){
            networkFactory = UrlConnectionFactory()
            getNetwork().doNetworkAsync()
        }
    }

    private fun getNetwork(): Network{
        Log.i(ConstConfigure.TAG, "url:" + networkFragmentView.getSepcialUrl() +
                ", state:" + networkFragmentView.getCheckBoxState())
        return networkFactory.createNetworkInstance(networkFragmentView.getSepcialUrl(), networkFragmentView.getCheckBoxState())
    }
}