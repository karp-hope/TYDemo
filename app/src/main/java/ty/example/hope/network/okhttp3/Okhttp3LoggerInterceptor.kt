package ty.example.hope.network.okhttp3

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import ty.example.hope.util.ConstConfigure

/**
 * @description: Okhttp3LoggerInterceptor
 **
 * @author: hope
 **
 * @create: 2019-03-13 16:40
 */
class Okhttp3LoggerInterceptor: Interceptor {
    override fun intercept(p0: Interceptor.Chain?): Response {
        var request: Request = p0!!.request()

        var t1 = System.currentTimeMillis()

        Log.i(ConstConfigure.TAG, "socket add:" + p0.connection()?.socket())
        Log.i(ConstConfigure.TAG, String.format("Sending request %s on %s%n%s",
                request.url(), p0.connection(), request.headers()))

        var response: Response = p0!!.proceed(request)
        var t2 = System.currentTimeMillis()

        Log.i(ConstConfigure.TAG, String.format("Received response for %s on %s in %d%n%s",
                response.request().url(), p0.connection(), (t2 - t1), response.headers()))

        return response
    }
}