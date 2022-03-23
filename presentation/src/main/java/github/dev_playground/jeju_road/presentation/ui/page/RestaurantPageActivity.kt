package github.dev_playground.jeju_road.presentation.ui.page

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import github.dev_playground.jeju_road.presentation.R
import github.dev_playground.jeju_road.presentation.databinding.ActivityRestaurantPageBinding
import github.dev_playground.jeju_road.presentation.ui.base.BaseActivity
import github.dev_playground.jeju_road.presentation.util.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RestaurantPageActivity : BaseActivity<ActivityRestaurantPageBinding>(
    R.layout.activity_restaurant_page
) {

    private val viewModel by viewModel<RestaurantPageViewModel>()
    private val adapter: RestaurantPageListAdapter by lazy { RestaurantPageListAdapter() }

    private val transitionName: String? by lazy { intent.getStringExtra(KEY_TRANSITION_NAME) }
    private val id: Long by lazy { intent.getLongExtra(KEY_RESTAURANT_ID, 0) }

    override fun onCreate(savedInstanceState: Bundle?) {
        makeTransition()
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbarRestaurantPage)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        binding {
            toolbarRestaurantPage.setNavigationOnClickListener {
                finishAfterTransition()
            }
            recyclerViewRestaurantPage.adapter = adapter
        }

        viewModel.apply {
            id.value = this@RestaurantPageActivity.id

            detailInformationState.observe { state ->
                state.onSuccess {
                    adapter.setDetailInformation(it)
                }
            }
        }
    }

    private fun makeTransition() {
        transitionName ?: return

        findViewById<View>(android.R.id.content).transitionName = transitionName
        addEnterMaterialSharedElementCallback()

        addMaterialSharedElementEnterTransition {
            addTarget(android.R.id.content)
            scrimColor = ContextCompat.getColor(this@RestaurantPageActivity, R.color.blue_medium)
            setAllContainerColors(
                ContextCompat.getColor(this@RestaurantPageActivity, R.color.white)
            )
            duration = resources.getInteger(R.integer.restaurant_page_anim_duration).toLong()
        }
        addMaterialSharedElementReturnTransition {
            addTarget(android.R.id.content)
            scrimColor = ContextCompat.getColor(this@RestaurantPageActivity, R.color.blue_medium)
            setAllContainerColors(
                ContextCompat.getColor(this@RestaurantPageActivity, R.color.white)
            )
            duration = resources.getInteger(R.integer.restaurant_page_anim_duration).toLong()
        }
    }

    companion object {
        const val KEY_RESTAURANT_ID = "restaurantId"
        const val KEY_TRANSITION_NAME = "transitionName"
    }

}
