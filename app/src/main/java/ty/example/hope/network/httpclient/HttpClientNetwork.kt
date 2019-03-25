package ty.example.hope.network.httpclient

import android.util.Log
import org.apache.http.HttpEntity
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.util.EntityUtils
import ty.example.hope.network.Network
import ty.example.hope.util.ConstConfigure
import ty.example.hope.util.ThreadPoolTool
import ty.example.hope.util.Tools

/**
 * @description: HttpClientNetwork
 **
 * @author: hope
 **
 * @create: 2019-03-19 11:35
 */
abstract class HttpClientNetwork : Network{

    private var httpclient: HttpClient
    init {
        httpclient = getHttpClient()
    }

    override fun doNetworkAsync() {
        doNetwork()
    }

    override fun doNetworkSync() {
        doNetwork()
    }

    fun doNetwork() {
        ThreadPoolTool.runTask(object: Runnable{
            override fun run() {
                var httpResponse = httpclient.execute(getHttpUriRequest())

                Log.i(ConstConfigure.TAG, EntityUtils.toString(httpResponse.entity))
//                Log.i(ConstConfigure.TAG, String(Tools.readInputStream(httpResponse.entity.content)))
            }
        })
    }

    private fun getHttpClient(): HttpClient = DefaultHttpClient()

    protected abstract fun getHttpUriRequest(): HttpUriRequest
}