package ty.example.hope.webview

import ty.example.hope.BasePresenter
import ty.example.hope.BaseView

/**
 * @description: WebViewFragmentContract
 **
 * @author: hope
 **
 * @create: 2019-03-13 19:32
 */
interface WebViewFragmentContract {

    interface Presenter : BasePresenter {
    }

    interface View : BaseView<Presenter> {
        fun configureWebView()

    }
}