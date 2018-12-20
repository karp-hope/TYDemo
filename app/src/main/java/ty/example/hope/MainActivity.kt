package ty.example.hope

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.networkbench.agent.impl.NBSAppAgent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Example of a call to a native method
        sample_text.text = stringFromJNI()
        NBSAppAgent.setLicenseKey("094e27493fb54536bee392598b1a4544")
                .withLocationServiceEnabled(true).start(this.applicationContext)
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
}
