package github.dev_playground.jeju_road.presentation.ui.list

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import github.dev_playground.jeju_road.domain.model.Content
import github.dev_playground.jeju_road.presentation.R
import github.dev_playground.jeju_road.presentation.databinding.FragmentRestaurantListBinding
import github.dev_playground.jeju_road.presentation.ui.base.BaseFragment
import github.dev_playground.jeju_road.presentation.ui.page.RestaurantDetailActivity
import github.dev_playground.jeju_road.presentation.ui.page.RestaurantDetailActivity.Companion.KEY_RESTAURANT_ID
import github.dev_playground.jeju_road.presentation.ui.page.RestaurantDetailActivity.Companion.KEY_TRANSITION_NAME
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class RestaurantListFragment : BaseFragment<FragmentRestaurantListBinding>(
    R.layout.fragment_restaurant_list
) {
    private val viewModel by sharedViewModel<RestaurantListViewModel>()
    private val restaurantListAdapter by lazy {
        RestaurantListAdapter { content, view ->
            onContentItemClick(content, view)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()

        with(viewModel) {
            contentListState.observe { state ->
                binding.swipeRefreshLayoutRestaurantList.isRefreshing = false
                addContentList(state.data ?: emptyList())
                setVisibilityShimmer(state.loading)
            }
            contentList.observe {
                restaurantListAdapter.submitList(it.distinctBy { item -> item.id })
            }
        }

        loadNewPageAtEndOfScroll()
    }

    private fun setUpView() {
        binding {
            with(recyclerViewRestaurantList) {
                adapter = restaurantListAdapter
                addItemDecoration(
                    RestaurantListItemDecoration()
                )
            }
            swipeRefreshLayoutRestaurantList.setOnRefreshListener {
                viewModel.refreshContentList()
            }
        }
    }

    private fun onContentItemClick(content: Content, sharedView: View) {
        val transitionName = ViewCompat.getTransitionName(sharedView)
        requireNotNull(transitionName)

        val intent = Intent(requireActivity(), RestaurantDetailActivity::class.java).apply {
            putExtra(KEY_RESTAURANT_ID, content.id)
            putExtra(KEY_TRANSITION_NAME, transitionName)
        }
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            requireActivity(),
            sharedView,
            transitionName,
        )

        requireActivity().startActivity(intent, options.toBundle())
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