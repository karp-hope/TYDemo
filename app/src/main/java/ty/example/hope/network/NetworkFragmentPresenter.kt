package ty.example.hope.network

import android.util.Log
import ty.example.hope.network.okhttp3.Okhttp3Factory
import ty.example.hope.network.okhttp3.Okhttp3NetworkGet
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
        networkFactory = Okhttp3Factory()
        getNetwork().doNetworkAsync()
    }

    private fun getNetwork(): Network{
        Log.i(ConstConfigure.TAG, "url:" + networkFragmentView.getSepcialUrl() +
                ", state:" + networkFragmentView.getCheckBoxState())
        return networkFactory.createNetworkInstance(networkFragmentView.getSepcialUrl(), networkFragmentView.getCheckBoxState())
    }
}