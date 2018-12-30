package ty.example.hope

/**
 * @description: BaseView
 **
 * @author: hope
 *
 * 设置一个模板函数，表明setPresenter中的参数是basePresenter的子类
 **
 * @create: 2018-12-30 00:41
 */
interface BaseView<T> {
    var presenter:T
}