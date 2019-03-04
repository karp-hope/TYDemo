package ty.example.hope.network

/**
 * @description: NetworkFragmentPresenter
 **
 * @author: hope
 **
 * @create: 2019-03-04 15:45
 */
class NetworkFragmentPresenter constructor(var networkFragmentView: NetworkFragmentContract.View):
        NetworkFragmentContract.Presenter{

    init {
        networkFragmentView.presenter = this
    }

    override fun start() {
        networkFragmentView.initView()
    }
}