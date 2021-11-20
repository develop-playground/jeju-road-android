package github.dev_playground.jeju_road.ui.page

import android.os.Bundle
import android.widget.Toolbar
import github.dev_playground.jeju_road.R
import github.dev_playground.jeju_road.data.model.Information
import github.dev_playground.jeju_road.databinding.ActivityRestaurantPageBinding
import github.dev_playground.jeju_road.ui.base.BaseActivity
import org.koin.android.viewmodel.ext.android.viewModel

class RestaurantPageActivity : BaseActivity<ActivityRestaurantPageBinding>(
    R.layout.activity_restaurant_page
) {

    companion object {
        const val KEY_RESTAURANT_INFO = "restaurantInfo"
    }

    private val viewModel by viewModel<RestaurantPageViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbarRestaurantPage)

        val restaurantInfo = intent.getSerializableExtra(KEY_RESTAURANT_INFO) as Information

        binding {
            name = restaurantInfo.name
            address = restaurantInfo.address
            introduction = restaurantInfo.introduction
        }

        viewModel.apply {
            id.value = restaurantInfo.id

            restaurantDetail.observe {
                loadingEventViewModel.setLoadingState(it) { detail ->
                    binding.information = detail.information
                }
            }
        }
    }

}