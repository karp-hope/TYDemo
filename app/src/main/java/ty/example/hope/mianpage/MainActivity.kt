package ty.example.hope.mianpage

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBar
import android.support.v7.widget.Toolbar
import android.view.ActionMode
import android.view.Menu
import android.view.MenuInflater
//import butterknife.BindView
import com.networkbench.agent.impl.NBSAppAgent
import ty.example.hope.R
import ty.example.hope.adapter.FmPageAdapter

class MainActivity : AppCompatActivity(), MainPageContract.View {
    override lateinit var presenter: MainPageContract.Presenter

    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configureMenu()

        viewPager = findViewById(R.id.viewpage)

        // Example of a call to a native method
        MainPagePresenter(this.applicationContext, this)
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }

    override fun configureViewPageAdapter() {
        viewPager.adapter = FmPageAdapter(this.supportFragmentManager)
    }

    override fun initView() {

    }

    private fun configureMenu(){
        supportActionBar?.setHomeButtonEnabled(false)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.navigationMode = ActionBar.NAVIGATION_MODE_TABS

        val tabListener = object : ActionBar.TabListener{
            override fun onTabSelected(tab: ActionBar.Tab?, ft: FragmentTransaction?) {

            }

            override fun onTabReselected(tab: ActionBar.Tab?, ft: FragmentTransaction?) {

            }

            override fun onTabUnselected(tab: ActionBar.Tab?, ft: FragmentTransaction?) {

            }
        }

        // Add 3 tabs, specifying the tab's text and TabListener
        for (i in 0 until 6) {
            supportActionBar?.addTab(
                    supportActionBar?.newTab()
                            ?.setText("Tab " + (i + 1))
                            ?.setTabListener(tabListener))
        }

    }
}
