package ty.example.hope.network.okhttp3

import android.util.Log
import okhttp3.*
import ty.example.hope.util.ConstConfigure
import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.Proxy

/**
 * @description: NBSEventListener
 **
 * @author: hope
 **
 * @create: 2019-03-05 17:42
 */
class NBSEventListener: EventListener() {

    fun printlnTag(name: String){
        Log.i(ConstConfigure.TAG, name + " time:" + System.currentTimeMillis())
    }

    override fun callStart(call: Call?) {
        printlnTag("callStart")
    }

    override fun callEnd(call: Call?) {
        printlnTag("callEnd")
    }

    override fun dnsStart(call: Call?, domainName: String?) {
        printlnTag("dnsStart")
    }

    override fun dnsEnd(call: Call?, domainName: String?, inetAddressList: MutableList<InetAddress>?) {
        printlnTag("dnsEnd")
    }

    override fun secureConnectStart(call: Call?) {
        printlnTag("secureConnectStart")
    }

    override fun secureConnectEnd(call: Call?, handshake: Handshake?) {
        printlnTag("secureConnectEnd")
    }

    override fun connectStart(call: Call?, inetSocketAddress: InetSocketAddress?, proxy: Proxy?) {
        printlnTag("connectStart")
    }

    override fun connectEnd(call: Call?, inetSocketAddress: InetSocketAddress?, proxy: Proxy?, protocol: Protocol?) {
        printlnTag("connectEnd")
    }

    override fun requestHeadersStart(call: Call?) {
        printlnTag("requestHeadersStart")
    }

    override fun requestHeadersEnd(call: Call?, request: Request?) {
        printlnTag("requestHeadersEnd")
    }

    override fun requestBodyStart(call: Call?) {
        printlnTag("requestBodyStart")
    }

    override fun requestBodyEnd(call: Call?, byteCount: Long) {
        printlnTag("requestBodyEnd")
    }

    override fun responseBodyStart(call: Call?) {
        printlnTag("responseBodyStart")
    }

    override fun responseBodyEnd(call: Call?, byteCount: Long) {
        printlnTag("responseBodyEnd")
    }

    override fun responseHeadersStart(call: Call?) {
        printlnTag("responseHeadersStart")
    }

    override fun responseHeadersEnd(call: Call?, response: Response?) {
        printlnTag("responseHeadersEnd")
    }

    override fun connectionAcquired(call: Call?, connection: Connection?) {
        printlnTag("connectionAcquired")
    }

    override fun connectionReleased(call: Call?, connection: Connection?) {
        printlnTag("connectionReleased")
    }
}