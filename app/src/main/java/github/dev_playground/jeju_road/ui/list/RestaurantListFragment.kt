package github.dev_playground.jeju_road.ui.list

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import github.dev_playground.jeju_road.R
import github.dev_playground.jeju_road.data.model.Information
import github.dev_playground.jeju_road.databinding.FragmentRestaurantListBinding
import github.dev_playground.jeju_road.ui.base.BaseFragment
import github.dev_playground.jeju_road.ui.component.VerticalDividerItemDecoration
import github.dev_playground.jeju_road.ui.loading.LoadingEventViewModel
import github.dev_playground.jeju_road.ui.page.RestaurantPageActivity
import github.dev_playground.jeju_road.ui.page.RestaurantPageActivity.Companion.KEY_RESTAURANT_INFO
import github.dev_playground.jeju_road.util.startActivity
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class RestaurantListFragment : BaseFragment<FragmentRestaurantListBinding>(
    R.layout.fragment_restaurant_list
) {
    private val viewModel by viewModel<RestaurantListViewModel>()
    private val loadingEventViewModel by sharedViewModel<LoadingEventViewModel>()
    private val adapter by lazy { RestaurantListAdapter(viewModel) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()

        viewModel.apply {
            restaurantList.observe {
                loadingEventViewModel.setLoadingState(it) { restaurants ->
                    binding.swipeRefreshLayoutRestaurantList.isRefreshing = false
                    adapter.submitList(restaurants.informationList)
                }

                binding.recyclerViewRestaurantList.addOnScrollListener(object :
                    RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)

                        val lastVisibleItemPosition =
                            (recyclerView.layoutManager as LinearLayoutManager)
                                .findLastCompletelyVisibleItemPosition()
                        val itemTotalCount = recyclerView.adapter?.itemCount?.minus(1)

                        if (!isLoading && lastVisibleItemPosition == itemTotalCount) {
                            isLoading = true
                            pagingRestaurantList()
                        }
                    }
                })
            }

            bringRestaurantList.observe {
                adapter.updateList(it.data?.informationList as MutableList<Information>)
            }

            onRestaurantClickEvent.eventObserve {
                requireActivity().startActivity<RestaurantPageActivity> {
                    putExtra(KEY_RESTAURANT_INFO, it)
                }
            }
        }
    }

    private fun setUpView() {
        binding {
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
        var isLoading = false
        fun newInstance() = RestaurantListFragment()
    }

}