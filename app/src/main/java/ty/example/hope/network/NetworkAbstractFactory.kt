package ty.example.hope.network

import ty.example.hope.network.httpclient.HttpClientNetworkGet
import ty.example.hope.network.httpclient.HttpClientNetworkPost

/**
 * @description: NetworkAbstractFactory
 **
 * @author: hope
 * 抽象工厂定义了一个接口，所有的具体的工厂都必须实现此接口，这个接口包含一组方法用来生产产品
 *
 * 这个下面只有一个产品
 **
 * @create: 2019-03-05 14:36
 */
abstract class NetworkAbstractFactory {
    fun createNetworkInstance(urlStr: String, httpMethodType: HttpMethodType): Network{
        when(httpMethodType){
            HttpMethodType.GET-> return doNetworkGet(urlStr)
            HttpMethodType.POST -> return doNetworkPost(urlStr)
            else->
                return doNetworkGet(urlStr)
        }
    }

    abstract fun doNetworkGet(urlStr: String): Network

    abstract fun doNetworkPost(urlStr: String): Network
}