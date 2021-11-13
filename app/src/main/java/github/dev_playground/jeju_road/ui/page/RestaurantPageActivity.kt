package github.dev_playground.jeju_road.ui.page

import android.os.Bundle
import github.dev_playground.jeju_road.R
import github.dev_playground.jeju_road.databinding.ActivityRestaurantPageBinding
import github.dev_playground.jeju_road.domain.model.Information
import github.dev_playground.jeju_road.ui.base.BaseActivity

class RestaurantPageActivity : BaseActivity<ActivityRestaurantPageBinding>(
    R.layout.activity_restaurant_page
) {

    companion object {
        const val RESTAURANT_INFO = "restaurantInfo"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        val restaurantInfo = intent.getSerializableExtra(RESTAURANT_INFO) as Information

        binding.apply {
            textViewActivityRestaurantPageName.text = restaurantInfo.name
        }
    }
}