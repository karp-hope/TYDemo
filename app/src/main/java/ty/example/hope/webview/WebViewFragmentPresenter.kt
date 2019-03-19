package ty.example.hope.webview

/**
 * @description: WebViewFragmentPresenter
 **
 * @author: hope
 **
 * @create: 2019-03-13 19:49
 */
class WebViewFragmentPresenter constructor(var webveiwView: WebViewFragmentContract.View) : WebViewFragmentContract.Presenter{

    init {
        webveiwView.presenter = this
    }

    override fun start() {
        webveiwView.configureWebView()
    }
}