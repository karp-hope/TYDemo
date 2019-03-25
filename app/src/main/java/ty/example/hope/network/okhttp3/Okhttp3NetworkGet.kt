package ty.example.hope.network.okhttp3

import android.util.Log
import okhttp3.*
import ty.example.hope.network.Network
import ty.example.hope.util.ConstConfigure
import java.io.IOException

/**
 * @description: Okhttp3NetworkGet
 **
 * @author: hope
 **
 * @create: 2019-03-05 10:02
 */
class Okhttp3NetworkGet constructor(var urlstr:String): Network{

//    private var client: OkHttpClient
//    init {
//        client = OkHttpClient.Builder()
//                .addInterceptor(Okhttp3LoggerInterceptor())
//                .addNetworkInterceptor(Okhttp3LoggerInterceptor())
////                .eventListener(NBSEventListener()).build()
//                .eventListenerFactory(HttpEventListener.FACTORY).build()
//
//    }

//    private lateinit var client: OkHttpClient

    companion object {
                var client = OkHttpClient.Builder()
                .addInterceptor(Okhttp3LoggerInterceptor())
//                .addNetworkInterceptor(Okhttp3LoggerInterceptor())
//                .eventListener(NBSEventListener()).build()
                .eventListenerFactory(HttpEventListener.FACTORY).build()
    }

    override fun doNetworkAsync() {
        Log.i(ConstConfigure.TAG, "doNetworkAsync ok3 begin newCall time:" + System.currentTimeMillis())
        client.newCall(getRequest()).enqueue(object : Callback {
            override fun onFailure(p0: Call?, p1: IOException?) {
                Log.e(ConstConfigure.TAG, "Okhttp3NetworkGet doNetworkAsync failed:" + p1?.message.toString())
            }

            override fun onResponse(p0: Call?, p1: Response?) {
                Log.i(ConstConfigure.TAG, "doNetworkAsync onResponse time:" + System.currentTimeMillis())
                if(p1!!.isSuccessful){
                    printlnResponseInfo(p1!!)
                }
                readResponseBody(p1!!)

            }
        })
    }

    override fun doNetworkSync() {
        try {
            doExecute()
        }catch (exception: Exception){
            Log.e(ConstConfigure.TAG, "doNetworkSync", exception)
        }
    }

    private fun doExecute(){
        var response = client.newCall(getRequest()).execute()

        if(!response.isSuccessful)
            throw Exception("Okhttp3NetworkGet doExecute failed")

        printlnResponseInfo(response)
    }

    private fun getRequest(): Request =
        Request.Builder().url(urlstr)
                .build()

    private fun printlnResponseInfo(response: Response){
//        printlnResponseHeaders(response.headers())
        Log.i(ConstConfigure.TAG, "responseBody class:" + response.body()!!::class)
//        Log.i(ConstConfigure.TAG, "responseBody:" + response.body()?.string())
        Log.i(ConstConfigure.TAG, "responseBody contentLength:" + response.body()?.contentLength())
    }

    private fun printlnResponseHeaders(responseHeaders: Headers){
        for (i in 0 .. (responseHeaders.size() - 1)){
            Log.i(ConstConfigure.TAG, "head name:" + responseHeaders.name(i)
                    + ", value:" + responseHeaders.value(i))
        }
    }

    private fun readResponseBody(response: Response){
        response.body()?.source()?.readByteString()
    }

}