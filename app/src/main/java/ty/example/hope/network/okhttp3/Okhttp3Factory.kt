package ty.example.hope.network.okhttp3

import ty.example.hope.network.HttpMethodType
import ty.example.hope.network.Network
import ty.example.hope.network.NetworkAbstractFactory

/**
 * @description: Okhttp3Factory
 **
 * @author: hope
 **
 * @create: 2019-03-05 14:39
 */
class Okhttp3Factory :NetworkAbstractFactory{

    override fun createNetworkInstance(urlStr: String, httpMethodType: HttpMethodType): Network {

        when (httpMethodType) {
            HttpMethodType.GET -> return Okhttp3NetworkGet(urlStr)
            HttpMethodType.POST -> return Okhttp3NetworkPost(urlStr)
            else -> return Okhttp3NetworkGet(urlStr)
        }
    }
}