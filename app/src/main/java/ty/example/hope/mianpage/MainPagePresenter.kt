package ty.example.hope.mianpage

import android.content.Context
import android.widget.Toast
import com.networkbench.agent.impl.NBSAppAgent

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
    }

    private fun initTy() {
        Toast.makeText(appContext, "1111", Toast.LENGTH_LONG).show()
        NBSAppAgent.setLicenseKey("094e27493fb54536bee392598b1a4544")
                .withLocationServiceEnabled(true).start(appContext)
    }
}