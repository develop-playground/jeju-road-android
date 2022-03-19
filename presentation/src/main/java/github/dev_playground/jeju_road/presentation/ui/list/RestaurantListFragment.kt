package github.dev_playground.jeju_road.presentation.ui.list

import android.os.Bundle
import android.view.View
import github.dev_playground.jeju_road.presentation.R
import github.dev_playground.jeju_road.presentation.databinding.FragmentRestaurantListBinding
import github.dev_playground.jeju_road.presentation.ui.base.BaseFragment
import github.dev_playground.jeju_road.presentation.ui.page.RestaurantPageActivity
import github.dev_playground.jeju_road.presentation.ui.page.RestaurantPageActivity.Companion.KEY_RESTAURANT_INFO
import github.dev_playground.jeju_road.presentation.util.startActivity
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class RestaurantListFragment : BaseFragment<FragmentRestaurantListBinding>(
    R.layout.fragment_restaurant_list
) {
    private val viewModel by sharedViewModel<RestaurantListViewModel>()
    private val restaurantListAdapter by lazy { RestaurantListAdapter(viewModel) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()

        viewModel.apply {
            contentListState.observe { state ->
                binding.swipeRefreshLayoutRestaurantList.isRefreshing = false
                addContentList(state.data ?: emptyList())
                setVisibilityShimmer(state.loading)
            }
            contentList.observe {
                restaurantListAdapter.submitList(it.distinctBy { item -> item.id })
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
                addItemDecoration(RestaurantListItemDecoration())
            }
            swipeRefreshLayoutRestaurantList.setOnRefreshListener {
                viewModel.refreshContentList()
            }
        }
    }

    private fun setVisibilityShimmer(isLoading: Boolean) {
        binding {
            when (isLoading) {
                true -> {
                    shimmerFrameLayoutRestaurantList.startShimmer()
                    shimmerFrameLayoutRestaurantList.visibility = View.VISIBLE
                    recyclerViewRestaurantList.visibility = View.INVISIBLE
                }
                else -> {
                    shimmerFrameLayoutRestaurantList.stopShimmer()
                    recyclerViewRestaurantList.visibility = View.VISIBLE
                    shimmerFrameLayoutRestaurantList.visibility = View.INVISIBLE
                }
            }
        }
    }

    private fun loadNewPageAtEndOfScroll() {
        binding {
            recyclerViewRestaurantList.setOnScrollChangeListener { _, _, _, _, _ ->
                if (!recyclerViewRestaurantList.canScrollVertically(1) &&
                    recyclerViewRestaurantList.adapter?.itemCount ?: 0 > 0
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