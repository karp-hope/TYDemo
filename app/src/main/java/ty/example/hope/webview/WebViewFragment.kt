package ty.example.hope.webview

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Spinner
import com.networkbench.agent.impl.instrumentation.NBSWebChromeClient
import ty.example.hope.R


/**
 * @description: WebViewFragment
 **
 * @author: hope
 **
 * @create: 2019-03-13 19:25
 */
class WebViewFragment: Fragment(), WebViewFragmentContract.View {

    override lateinit var presenter: WebViewFragmentContract.Presenter

    private lateinit var webView: WebView
    private lateinit var spinner: Spinner

    init {

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var result = inflater!!.inflate(R.layout.fragment_webview, container, false)

        webView = result.findViewById(R.id.wv_page)
        spinner = result.findViewById(R.id.sp_webview)
        WebViewFragmentPresenter(this)

        presenter.start()

        return result
    }

    override fun configureWebView() {
        webView.webViewClient = object : WebViewClient(){

        }

        webView.webChromeClient = object : WebChromeClient(){
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
//                NBSWebChromeClient.initJSMonitor(view, newProgress)
            }
        }

//        webView.loadUrl("https://www.baidu.com")


    }

}