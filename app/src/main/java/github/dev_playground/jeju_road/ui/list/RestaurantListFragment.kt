package github.dev_playground.jeju_road.ui.list

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import github.dev_playground.jeju_road.R
import github.dev_playground.jeju_road.databinding.FragmentRestaurantListBinding
import github.dev_playground.jeju_road.ui.base.BaseFragment
import github.dev_playground.jeju_road.ui.component.VerticalDividerItemDecoration
import github.dev_playground.jeju_road.ui.loading.LoadingEventViewModel
import github.dev_playground.jeju_road.ui.page.RestaurantPageActivity
import github.dev_playground.jeju_road.ui.page.RestaurantPageActivity.Companion.KEY_RESTAURANT_INFO
import github.dev_playground.jeju_road.util.startActivity
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class RestaurantListFragment : BaseFragment<FragmentRestaurantListBinding>(
    R.layout.fragment_restaurant_list
) {
    private val viewModel by sharedViewModel<RestaurantListViewModel>()
    private val loadingEventViewModel by sharedViewModel<LoadingEventViewModel>()
    private val restaurantListAdapter by lazy { RestaurantListAdapter(viewModel) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()

        viewModel.apply {
            contentListState.observe {
                loadingEventViewModel.setLoadingState(it) { contentList ->
                    binding.swipeRefreshLayoutRestaurantList.isRefreshing = false
                    addContentList(contentList)
                }
            }
            contentList.observe {
                restaurantListAdapter.submitList(it)
            }
            onRestaurantClickEvent.eventObserve {
                requireActivity().startActivity<RestaurantPageActivity> {
                    putExtra(KEY_RESTAURANT_INFO, it)
                }
            }
        }

        loadNewPageAtEndOfScroll()
    }

    private fun setUpView() {
        binding {
            recyclerViewRestaurantList.run {
                adapter = restaurantListAdapter
                addItemDecoration(VerticalDividerItemDecoration(requireContext()))
            }

            swipeRefreshLayoutRestaurantList.setOnRefreshListener {
                viewModel.refreshContentList()
            }
        }
    }

    private fun loadNewPageAtEndOfScroll() {
        binding {
            recyclerViewRestaurantList.setOnScrollChangeListener { _, _, _, _, _ ->
                val layoutManager = recyclerViewRestaurantList.layoutManager as LinearLayoutManager

                val lastVisibleItemPosition = layoutManager.findLastCompletelyVisibleItemPosition()
                val itemTotalCount = restaurantListAdapter.itemCount.minus(1)

                if (lastVisibleItemPosition == itemTotalCount &&
                    !binding.swipeRefreshLayoutRestaurantList.isRefreshing
                ) {
                    viewModel.fetchContentList()
                }
            }
        }
    }

    companion object {
        fun newInstance() = RestaurantListFragment()
    }

}