package github.dev_playground.jeju_road.ui.image

import android.os.Bundle
import github.dev_playground.jeju_road.R
import github.dev_playground.jeju_road.databinding.ActivityFullSizeImageBinding
import github.dev_playground.jeju_road.ui.base.BaseActivity

class FullSizeImageActivity: BaseActivity<ActivityFullSizeImageBinding>(
    R.layout.activity_full_size_image
) {

    private val url by lazy { intent.getStringExtra(KEY_URL) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding {
            imageButtonFullSizeImage.setOnClickListener {
                finish()
            }
        }
        setUrl()
    }

    private fun setUrl() {
        url?.let {
            binding.url = it
        }
    }

    companion object {
        const val KEY_URL = "key_url"
    }

}