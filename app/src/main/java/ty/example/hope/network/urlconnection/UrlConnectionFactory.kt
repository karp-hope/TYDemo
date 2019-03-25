package ty.example.hope.network.urlconnection

import ty.example.hope.network.HttpMethodType
import ty.example.hope.network.Network
import ty.example.hope.network.NetworkAbstractFactory

/**
 * @description: UrlConnectionFactory
 **
 * @author: hope
 **
 * @create: 2019-03-13 18:56
 */
class UrlConnectionFactory: NetworkAbstractFactory() {

    override fun doNetworkPost(urlStr: String): Network {
        return UrlConnectionNetworkGet(urlStr)
    }

    override fun doNetworkGet(urlStr: String): Network {
        return UrlConnectionNetworkGet(urlStr)
    }

}