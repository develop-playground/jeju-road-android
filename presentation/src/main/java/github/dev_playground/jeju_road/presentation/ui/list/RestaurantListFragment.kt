package github.dev_playground.jeju_road.presentation.ui.list

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import github.dev_playground.jeju_road.domain.model.Content
import github.dev_playground.jeju_road.presentation.R
import github.dev_playground.jeju_road.presentation.databinding.FragmentRestaurantListBinding
import github.dev_playground.jeju_road.presentation.ui.base.BaseFragment
import github.dev_playground.jeju_road.presentation.ui.page.RestaurantDetailActivity
import github.dev_playground.jeju_road.presentation.ui.page.RestaurantDetailActivity.Companion.KEY_RESTAURANT_ID
import github.dev_playground.jeju_road.presentation.ui.page.RestaurantDetailActivity.Companion.KEY_TRANSITION_NAME
import github.dev_playground.jeju_road.presentation.util.UiState
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
        binding {
            bindList(
                restaurantListAdapter,
                viewModel.contentList,
                viewModel.recyclerState,
                viewModel::fetchContentList,
                viewModel::saveState
            )
            bindSwipeRefresh(viewModel::refreshContentList)
            bindShimmer(viewModel.contentListState)
        }

        viewModel.contentListState.observe {
            viewModel.addContentList(it.data ?: emptyList())
        }
    }

    private fun FragmentRestaurantListBinding.bindList(
        adapter: RestaurantListAdapter,
        contentList: LiveData<List<Content>>,
        savedState: LiveData<Parcelable?>,
        fetchContentList: () -> Unit,
        saveState: (Parcelable?) -> Unit
    ) {
        with(recyclerViewRestaurantList) {
            this.adapter = adapter
            addItemDecoration(
                RestaurantListItemDecoration()
            )
            setHasFixedSize(true)
            setOnScrollChangeListener { _, _, _, _, _ ->
                if (!canScrollVertically(1) && adapter.itemCount > 0) {
                    fetchContentList()
                }
            }
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    saveState(layoutManager?.onSaveInstanceState())
                }
            })
        }

        contentList.observe {
            restaurantListAdapter.submitList(it)
            savedState.value?.let { state ->
                recyclerViewRestaurantList.layoutManager?.onRestoreInstanceState(state)
            }
        }
    }

    private fun FragmentRestaurantListBinding.bindSwipeRefresh(
        refresh: () -> Unit
    ) {
        swipeRefreshLayoutRestaurantList.setOnRefreshListener {
            refresh()
            swipeRefreshLayoutRestaurantList.isRefreshing = false
        }
    }

    private fun FragmentRestaurantListBinding.bindShimmer(
        contentUiState: LiveData<UiState<List<Content>>>
    ) {
        contentUiState.observe {
            when (it.loading) {
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

    companion object {
        fun newInstance() = RestaurantListFragment()
    }

}