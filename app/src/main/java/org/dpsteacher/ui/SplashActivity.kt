package org.dpsteacher.ui

import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import org.dpsteacher.R
import org.dpsteacher.ui.authenticaton.LoginActivity
import org.dpsteacher.utils.TeacherSharedPref
import org.dpsteacher.utils.startNewActivityFinish

class SplashActivity : AppCompatActivity() {
    private val spTeacher by lazy { TeacherSharedPref.instance }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash)

        startNewActivityFinish(MainActivity::class.java)

       /* Handler().postDelayed({
            when (spTeacher.isLoginStatus) {
                1 -> {
                    startNewActivityFinish(MainActivity::class.java)
                }
                else -> {
                    startNewActivityFinish(LoginActivity::class.java)
                }
            }
        }, 1000)
*/

    }
}