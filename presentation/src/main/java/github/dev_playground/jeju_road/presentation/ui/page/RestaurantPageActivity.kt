package github.dev_playground.jeju_road.presentation.ui.page

import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import github.dev_playground.jeju_road.domain.model.Content
import github.dev_playground.jeju_road.presentation.R
import github.dev_playground.jeju_road.presentation.databinding.ActivityRestaurantPageBinding
import github.dev_playground.jeju_road.presentation.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class RestaurantPageActivity : BaseActivity<ActivityRestaurantPageBinding>(
    R.layout.activity_restaurant_page
) {

    companion object {
        const val KEY_RESTAURANT_INFO = "restaurantInfo"
        const val KEY_TRANSITION_NAME = "transitionName"
    }

    private val viewModel by viewModel<RestaurantPageViewModel>()
    private lateinit var adapter: RestaurantPageListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        startTransition()
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbarRestaurantPage)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        binding.toolbarRestaurantPage.setNavigationOnClickListener {
            finishAfterTransition()
        }

        setRecyclerView()

        viewModel.apply {
            (intent.getSerializableExtra(KEY_RESTAURANT_INFO) as? Content)?.let {
                id.value = it.id
            }

            detailInformationState.observe { state ->
                state.data?.let {
                    adapter.setDetailInformation(it)
                }
            }
        }
    }

    private fun startTransition() {
        intent.getStringExtra(KEY_TRANSITION_NAME)?.let {
            findViewById<View>(android.R.id.content).transitionName = it
        }

        setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())

        window.sharedElementEnterTransition = MaterialContainerTransform().apply {
            addTarget(android.R.id.content)
            scrimColor = Color.WHITE
            setAllContainerColors(Color.WHITE)
            duration = 300L
        }
        window.sharedElementReturnTransition = MaterialContainerTransform().apply {
            addTarget(android.R.id.content)
            scrimColor = Color.WHITE
            setAllContainerColors(Color.WHITE)
            duration = 250L
        }
    }

    private fun setRecyclerView() {
        adapter = RestaurantPageListAdapter()
        binding.recyclerViewRestaurantPage.adapter = adapter
    }

}