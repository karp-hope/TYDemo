package ty.example.hope.launch

import ty.example.hope.BasePresenter
import ty.example.hope.BaseView

/**
 * @description: SplashActivityContract
 * this class is used for the contract between presenter and view
 **
 * @author: hope
 **
 * @create: 2018-12-30 00:23
 */

interface SplashActivityContract{

    interface View: BaseView<Presenter>{

    }

    interface Presenter: BasePresenter{

    }

}