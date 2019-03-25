package ty.example.hope.network.httpclient

import org.apache.http.client.methods.HttpPost
import org.apache.http.client.methods.HttpUriRequest

/**
 * @description: HttpClientNetworkPost
 **
 * @author: hope
 **
 * @create: 2019-03-19 12:08
 */
class HttpClientNetworkPost constructor(var urlStr: String): HttpClientNetwork(){
    override fun getHttpUriRequest(): HttpUriRequest  = HttpPost(urlStr)
}