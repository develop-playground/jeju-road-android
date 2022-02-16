package github.dev_playground.jeju_road.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import github.dev_playground.jeju_road.R
import github.dev_playground.jeju_road.databinding.FragmentRestaurantListBinding
import github.dev_playground.jeju_road.ui.component.VerticalDividerItemDecoration
import github.dev_playground.jeju_road.ui.loading.LoadingEventViewModel
import github.dev_playground.jeju_road.util.dataBinding
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class RestaurantListFragment: Fragment(R.layout.fragment_restaurant_list) {

    private val binding by dataBinding(FragmentRestaurantListBinding::bind)
    private val viewModel by viewModel<RestaurantListViewModel>()
    private val loadingEventViewModel by sharedViewModel<LoadingEventViewModel>()
    private val adapter by lazy { RestaurantListAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        setUpView()

        viewModel.restaurantList.observe(viewLifecycleOwner) {
            loadingEventViewModel.setLoadingState(it) { restaurants ->
                binding.swipeRefreshLayoutRestaurantList.isRefreshing = false
                adapter.submitList(restaurants.informationList)
            }
        }
    }

    private fun setUpView() {
        with(binding) {
            recyclerViewRestaurantList.apply {
                adapter = this@RestaurantListFragment.adapter
                addItemDecoration(VerticalDividerItemDecoration(requireContext()))
            }

            swipeRefreshLayoutRestaurantList.setOnRefreshListener {
                viewModel.fetchRestaurantList()
            }
        }
    }

    companion object {
        fun newInstance() = RestaurantListFragment()
    }

}