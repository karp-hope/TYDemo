package ty.example.hope.network

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ty.example.hope.R

/**
 * @description: NetworkFragment
 **
 * @author: hope
 **
 * @create: 2019-02-27 16:39
 */
class NetworkFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater?.inflate(R.layout.fragment_network, container, false)
}