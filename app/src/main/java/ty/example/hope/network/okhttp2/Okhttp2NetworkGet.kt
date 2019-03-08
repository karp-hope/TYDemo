package ty.example.hope.network.okhttp2

import android.util.Log
import com.squareup.okhttp.Callback
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import com.squareup.okhttp.Response
import ty.example.hope.network.Network
import ty.example.hope.util.ConstConfigure
import java.io.IOException

/**
 * @description: Okhttp2NetworkGet
 **
 * @author: hope
 **
 * @create: 2019-03-06 08:57
 */
class Okhttp2NetworkGet constructor(var urlStr: String): Network{

    private val client = OkHttpClient()

    override fun doNetworkAsync() {
        client.newCall(getRequest()).enqueue(object :Callback{
            override fun onFailure(p0: Request?, p1: IOException?) {
                Log.i(ConstConfigure.TAG, "Okhttp2NetworkGet onFailure :" + p1?.message)
            }

            override fun onResponse(p0: Response?) {
                Log.i(ConstConfigure.TAG, "Okhttp2NetworkGet onResponse")
                if(!p0!!.isSuccessful)
                    Log.i(ConstConfigure.TAG, "Okhttp2NetworkGet onResponse failed")

                printResponseInfo(p0)
            }
        })
    }

    override fun doNetworkSync() {
        var response = client.newCall(getRequest()).execute()
        if(!response.isSuccessful)
            Log.i(ConstConfigure.TAG, "Okhttp2NetworkGet doNetworkSync failed")

        printResponseInfo(response)
    }

    private fun getRequest(): Request {
        return Request.Builder().url(urlStr).build()
    }

    private fun printResponseInfo(response: Response){
        Log.i(ConstConfigure.TAG, "responseBody length:" + response.body().contentLength())
        Log.i(ConstConfigure.TAG, "responseBody message:" + response.body().string())
    }

}