package ty.example.hope.mainpage

import ty.example.hope.BasePresenter
import ty.example.hope.BaseView

/**
 * @description: MainPageContract
 **
 * @author: hope
 **
 * @create: 2019-02-27 14:43
 */
interface MainPageContract {

    interface Presenter: BasePresenter{

    }

    interface View: BaseView<Presenter>{
        fun initView()
    }
}