package github.dev_playground.jeju_road.ui.page

import android.os.Bundle
import github.dev_playground.jeju_road.R
import github.dev_playground.jeju_road.data.model.Information
import github.dev_playground.jeju_road.databinding.ActivityRestaurantPageBinding
import github.dev_playground.jeju_road.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class RestaurantPageActivity : BaseActivity<ActivityRestaurantPageBinding>(
    R.layout.activity_restaurant_page
) {

    companion object {
        const val KEY_RESTAURANT_INFO = "restaurantInfo"
    }

    private val viewModel by viewModel<RestaurantPageViewModel>()
    private lateinit var adapter: RestaurantPageListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbarRestaurantPage)

        setRecyclerView()

        viewModel.apply {
            (intent.getSerializableExtra(KEY_RESTAURANT_INFO) as? Information)?.let {
                id.value = it.id
            }

            restaurantDetail.observe {
                loadingEventViewModel.setLoadingState(it) { detail ->
                    adapter.setDetailInformation(detail.information)
                }
            }
        }
    }

    private fun setRecyclerView() {
        adapter = RestaurantPageListAdapter()
        binding.recyclerViewRestaurantPage.adapter = adapter
    }

}