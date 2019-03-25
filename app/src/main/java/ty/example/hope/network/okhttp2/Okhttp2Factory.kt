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
class Okhttp2Factory: NetworkAbstractFactory(){
    override fun doNetworkPost(urlStr: String): Network {
        return Okhttp2NetworkGet(urlStr)
    }

    override fun doNetworkGet(urlStr: String): Network {
        return Okhttp2NetworkGet(urlStr)
    }

}