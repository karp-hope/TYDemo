package ty.example.hope.network.httpclient

import ty.example.hope.network.HttpMethodType
import ty.example.hope.network.Network
import ty.example.hope.network.NetworkAbstractFactory

/**
 * @description: HttpClientFactory
 **
 * @author: hope
 **
 * @create: 2019-03-19 12:09
 */
class HttpClientFactory: NetworkAbstractFactory() {
    override fun doNetworkPost(urlStr: String): Network {
        return HttpClientNetworkPost(urlStr)
    }

    override fun doNetworkGet(urlStr: String): Network {
        return HttpClientNetworkGet(urlStr)
    }
}