package github.dev_playground.jeju_road.presentation.ui.error

import android.content.Intent
import android.os.Bundle
import github.dev_playground.jeju_road.presentation.R
import github.dev_playground.jeju_road.presentation.databinding.ActivityGlobalErrorBinding
import github.dev_playground.jeju_road.presentation.ui.base.BaseActivity
import github.dev_playground.jeju_road.presentation.util.startActivity

class GlobalErrorActivity
    : BaseActivity<ActivityGlobalErrorBinding>(R.layout.activity_global_error) {

    private val lastActivityIntent by lazy { intent.getParcelableExtra<Intent>(EXTRA_INTENT) }
    private val errorText by lazy { intent.getStringExtra(EXTRA_ERROR_TEXT) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding {
            buttonRetryGlobalError.setOnClickListener {
                startActivity(lastActivityIntent)
                finish()
            }
        }
    }

    companion object {
        const val EXTRA_INTENT = "EXTRA_INTENT"
        const val EXTRA_ERROR_TEXT = "EXTRA_ERROR_TEXT"
    }
}