package github.dev_playground.jeju_road.ui.page

import android.os.Bundle
import android.widget.ProgressBar
import github.dev_playground.jeju_road.R
import github.dev_playground.jeju_road.data.model.Information
import github.dev_playground.jeju_road.databinding.ActivityRestaurantPageBinding
import github.dev_playground.jeju_road.ui.base.BaseActivity

class RestaurantPageActivity : BaseActivity<ActivityRestaurantPageBinding>(
    R.layout.activity_restaurant_page
) {

    companion object {
        const val KEY_RESTAURANT_INFO = "restaurantInfo"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val restaurantInfo = intent.getSerializableExtra(KEY_RESTAURANT_INFO) as Information

        binding {
            information = restaurantInfo
        }
    }

}