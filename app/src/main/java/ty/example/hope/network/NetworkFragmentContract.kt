package ty.example.hope.network

import ty.example.hope.BasePresenter
import ty.example.hope.BaseView

/**
 * @description: NetworkFragmentContract
 **
 * @author: hope
 **
 * @create: 2019-03-04 15:16
 */
interface NetworkFragmentContract {

    interface Presenter: BasePresenter{

    }

    interface View: BaseView<Presenter> {
        fun initView()
    }
}