package ty.example.hope.launch

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ty.example.hope.mianpage.MainActivity

/**
 * @description: SplashActivity
 **
 * SplashActivity没有布局文件的主要思想是因为为了快速的显示界面给客户作为缓冲
 *
 * @author: hope
 **
 * @create: 2018-12-30 00:17
 */
class SplashActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //注意kotlin的写法
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}