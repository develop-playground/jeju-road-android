package github.hongbeomi.jeju_road.ui.list

import android.os.Bundle
import android.view.View
import github.hongbeomi.jeju_road.R
import github.hongbeomi.jeju_road.databinding.FragmentRestaurantListBinding
import github.hongbeomi.jeju_road.ui.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class RestaurantListFragment: BaseFragment<FragmentRestaurantListBinding>(
    R.layout.fragment_restaurant_list
) {

    private val viewModel by viewModel<RestaurantListViewModel>()
    private val adapter by lazy { RestaurantListRecyclerAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpView()
        viewModel.restaurantList.observe {
            it.data?.let { restaurants ->
                adapter.submitList(restaurants.informationList)
            }
        }
    }

    fun setUpView() {
        binding {
            recyclerViewRestaurantList.adapter = adapter
        }
    }

    companion object {
        fun newInstance() = RestaurantListFragment()
    }

}