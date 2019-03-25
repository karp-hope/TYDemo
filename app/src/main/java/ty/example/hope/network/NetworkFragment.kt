package ty.example.hope.network

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
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
    private lateinit var btnView: Button

    private lateinit var cbGet: CheckBox
    private lateinit var cbPost: CheckBox

    override fun initView() {
        initSpinner()
        initAutoCompleteText()
        initBtnEvent()
        initCheckBox()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var result = inflater!!.inflate(R.layout.fragment_network, container, false)
        spinner = result.findViewById(R.id.sp_networklib)
        textView = result.findViewById(R.id.tv_autocomplete)
        btnView = result.findViewById(R.id.network_btn_send)

        cbGet = result.findViewById(R.id.cb_method_get)
        cbPost = result.findViewById(R.id.cb_method_post)

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

    fun initBtnEvent(){
        btnView.setOnClickListener({
            presenter.doNetwork()
        })
    }

    fun initCheckBox(){
        cbGet.setOnClickListener{onCheckboxClicked(cbGet)}
        cbPost.setOnClickListener{onCheckboxClicked(cbPost)}
    }

    override fun getSepcialUrl():String {
        return textView.text.toString()
    }

    override fun getHttpLibType(): String {
        return spinner.selectedItem.toString()
    }

    override fun getCheckBoxState(): HttpMethodType {
        if(cbGet.isChecked)
            return HttpMethodType.GET
        else
            return HttpMethodType.POST
    }

    fun onCheckboxClicked(view: View){
        if(view is CheckBox){
            when(view.id){
                R.id.cb_method_get -> {
                    if(!view.isChecked) {
                        view.isChecked = true
                    }
                    cbPost.isChecked = false
                }

                R.id.cb_method_post -> {
                    if(!view.isChecked) {
                        view.isChecked = true
                    }
                    cbGet.isChecked = false
                }

            }
        }
    }
}