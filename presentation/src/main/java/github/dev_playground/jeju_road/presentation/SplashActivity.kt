package github.dev_playground.jeju_road.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import github.dev_playground.jeju_road.presentation.ui.main.MainActivity
import github.dev_playground.jeju_road.presentation.util.startActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContentView(R.layout.activity_splash)

        CoroutineScope(Dispatchers.IO).launch {
            delay(2000)

            this@SplashActivity.startActivity<MainActivity> {  }
            finish()
        }
    }
}