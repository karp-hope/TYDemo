package ty.example.hope.network.httpclient

import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpUriRequest

/**
 * @description: HttpClientNetworkGet
 **
 * @author: hope
 **
 * @create: 2019-03-19 12:05
 */
class HttpClientNetworkGet constructor(var urlStr: String): HttpClientNetwork(){
    override fun getHttpUriRequest(): HttpUriRequest = HttpGet(urlStr)
}