package ty.example.hope.network.okhttp2

import ty.example.hope.network.HttpMethodType
import ty.example.hope.network.Network
import ty.example.hope.network.NetworkAbstractFactory

/**
 * @description: Okhttp2Factory
 **
 * @author: hope
 **
 * @create: 2019-03-06 09:25
 */
class Okhttp2Factory: NetworkAbstractFactory{
    override fun createNetworkInstance(urlStr: String, httpMethodType: HttpMethodType): Network {
        when (httpMethodType) {
            HttpMethodType.GET -> return Okhttp2NetworkGet(urlStr)
            else -> return Okhttp2NetworkGet(urlStr)
        }
    }

}