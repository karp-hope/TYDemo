package ty.example.hope.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import ty.example.hope.crash.CrashFragment
import ty.example.hope.network.NetworkFragment
import ty.example.hope.webview.WebViewFragment

/**
 * @description: FmPageAdapter
 **
 * @author: hope
 **
 * @create: 2019-02-27 15:49
 */
class FmPageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment{
        if(position == 0)
            return CrashFragment()
        else if(position == 1)
            return NetworkFragment()
        else
            return WebViewFragment()
    }
}