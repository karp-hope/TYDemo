package ty.example.hope.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import ty.example.hope.crash.CrashFragment
import ty.example.hope.network.NetworkFragment

/**
 * @description: FmPageAdapter
 **
 * @author: hope
 **
 * @create: 2019-02-27 15:49
 */
class FmPageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment{
        if(position == 0)
            return CrashFragment()
        else
            return NetworkFragment()
    }
}