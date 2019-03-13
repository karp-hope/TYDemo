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
class UrlConnectionFactory: NetworkAbstractFactory {

    override fun createNetworkInstance(urlStr: String, httpMethodType: HttpMethodType): Network {
        when (httpMethodType) {
            HttpMethodType.GET -> {
                return UrlConnectionNetworkGet(urlStr)
            }
            else -> {
                return UrlConnectionNetworkGet(urlStr)
            }
        }
    }

}