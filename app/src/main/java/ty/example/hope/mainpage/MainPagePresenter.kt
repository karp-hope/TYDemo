package ty.example.hope.mainpage

import android.content.Context
import com.networkbench.agent.impl.NBSAppAgent
import com.newrelic.agent.android.NewRelic

/**
 * @description: MainPagePresenter
 **
 * @author: hope
 **
 * @create: 2019-02-27 14:48
 */
class MainPagePresenter constructor(private val appContext: Context,
                                    private val mainpageView: MainPageContract.View
) : MainPageContract.Presenter {

    init {
        mainpageView.presenter = this
    }


    override fun start() {
        initTy()
        initNewrelic()
        mainpageView.initView()
    }

    private fun initTy() {
        NBSAppAgent.setLicenseKey("094e27493fb54536bee392598b1a4544")
                .withLocationServiceEnabled(true).start(appContext)
    }

    private fun initNewrelic(){
        NewRelic.withApplicationToken("AA00ddeba424a09385178de3ad11c6301060f1125d").start(appContext.applicationContext)
    }


}