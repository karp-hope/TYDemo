package ty.example.hope.network.urlconnection

import android.util.Log
import com.networkbench.agent.impl.harvest.RequestMethodType
import ty.example.hope.network.Network
import ty.example.hope.util.ConstConfigure
import ty.example.hope.util.ThreadPoolTool
import ty.example.hope.util.Tools
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.ByteArrayOutputStream
import java.net.HttpURLConnection
import java.net.URL

/**
 * @description: UrlConnectionNetworkGet
 **
 * @author: hope
 **
 * @create: 2019-03-13 18:22
 */
class UrlConnectionNetworkGet(var urlStr: String) :Network{

    private lateinit var urlConnection: HttpURLConnection

    init {

    }

    override fun doNetworkAsync() {
        ThreadPoolTool.runTask(object: Runnable{
            override fun run() {
                urlConnection = URL(urlStr).openConnection() as HttpURLConnection
                urlConnection.doInput = true
                urlConnection.requestMethod = "GET"

                var result = Tools.readInputStream(urlConnection.inputStream)

                Log.i(ConstConfigure.TAG, "result:" + String(result))
            }
        })
    }

    override fun doNetworkSync() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}