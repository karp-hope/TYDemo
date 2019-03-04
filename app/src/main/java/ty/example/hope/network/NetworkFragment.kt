package ty.example.hope.network

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Spinner
import ty.example.hope.R

/**
 * @description: NetworkFragment
 **
 * @author: hope
 **
 * @create: 2019-02-27 16:39
 */
class NetworkFragment : Fragment(), NetworkFragmentContract.View{

    override lateinit var presenter: NetworkFragmentContract.Presenter
    private lateinit var spinner: Spinner
    private lateinit var textView: AutoCompleteTextView

    override fun initView() {
        initSpinner()
        initAutoCompleteText()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var result = inflater?.inflate(R.layout.fragment_network, container, false)
        spinner = result!!.findViewById(R.id.sp_networklib)
        textView = result!!.findViewById(R.id.tv_autocomplete)


        NetworkFragmentPresenter(this)
        presenter.start()

        return result
    }

    fun initSpinner(){
        var adapter = ArrayAdapter.createFromResource(this.context, R.array.sp_libs_type,
                android.R.layout.simple_spinner_dropdown_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    fun initAutoCompleteText(){
        val urls: Array<out String> = resources.getStringArray(R.array.url_arrays)

        ArrayAdapter<String>(this.context, android.R.layout.simple_list_item_1, urls).also{ adapter ->
            textView.setAdapter(adapter)
        }
    }


}