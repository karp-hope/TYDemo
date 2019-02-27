package ty.example.hope.crash

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ty.example.hope.R

/**
 * @description: CrashFragment
 **
 * @author: hope
 **
 * @create: 2019-02-27 16:10
 */
class CrashFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        return inflater!!.inflate(R.layout.fragment_crash, container, false)
    }
}