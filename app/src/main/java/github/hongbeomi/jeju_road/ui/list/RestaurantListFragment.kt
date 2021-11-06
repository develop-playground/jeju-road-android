package github.hongbeomi.jeju_road.ui.list

import android.os.Bundle
import android.view.View
import github.hongbeomi.jeju_road.R
import github.hongbeomi.jeju_road.databinding.FragmentRestaurantListBinding
import github.hongbeomi.jeju_road.ui.base.BaseFragment
import github.hongbeomi.jeju_road.ui.loading.LoadingEventViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class RestaurantListFragment: BaseFragment<FragmentRestaurantListBinding>(
    R.layout.fragment_restaurant_list
) {

    private val viewModel by viewModel<RestaurantListViewModel>()
    private val loadingEventViewModel by sharedViewModel<LoadingEventViewModel>()
    private val adapter by lazy { RestaurantListAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()

        viewModel.restaurantList.observe {
            loadingEventViewModel.setLoadingState(it) { restaurants ->
                adapter.submitList(restaurants.informationList)
            }
        }
    }

    private fun setUpView() {
        binding.recyclerViewRestaurantList.adapter = adapter
    }

    companion object {
        fun newInstance() = RestaurantListFragment()
    }

}