package ty.example.hope.network.urlconnection

import android.util.Log
import com.networkbench.agent.impl.harvest.RequestMethodType
import ty.example.hope.network.Network
import ty.example.hope.util.ConstConfigure
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
        Thread(object: Runnable{
            override fun run() {
                urlConnection = URL(urlStr).openConnection() as HttpURLConnection
                urlConnection.doInput = true
                urlConnection.requestMethod = "GET"

                var result = readInputStream()

                Log.i(ConstConfigure.TAG, "result:" + String(result))
            }
        }).start()
    }

    override fun doNetworkSync() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun readInputStream(): ByteArray{

        var bytes = ByteArray(1024)
        var bis = BufferedInputStream(urlConnection.inputStream)

        var baos = ByteArrayOutputStream()
        var bos = BufferedOutputStream(baos)
        var length = bis.read(bytes)
        try {
            while (length > 0){
                bos.write(bytes, 0, length)
                length = bis.read(bytes)
            }
            bos.flush()
            return baos.toByteArray()
        }finally {
            bis.close()
            bos.close()
            baos.close()
        }
        return ByteArray(0)
    }
}