package github.dev_playground.jeju_road.presentation.ui.page

import android.graphics.RectF
import android.os.Bundle
import android.transition.ChangeTransform
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.shape.ShapeAppearanceModel
import github.dev_playground.jeju_road.presentation.R
import github.dev_playground.jeju_road.presentation.databinding.ActivityRestaurantPageBinding
import github.dev_playground.jeju_road.presentation.model.RestaurantDetailInformationModel
import github.dev_playground.jeju_road.presentation.model.RestaurantIntroductionModel
import github.dev_playground.jeju_road.presentation.ui.base.BaseActivity
import github.dev_playground.jeju_road.presentation.ui.list.RestaurantListItemDecoration
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
            recyclerViewRestaurantPage.addItemDecoration(
                RestaurantListItemDecoration(
                    spaceResId = R.dimen.restaurant_detail_list_item_space,
                    color = ContextCompat.getColor(this@RestaurantPageActivity, R.color.lightGray)
                )
            )
        }

        with(viewModel) {
            id.value = this@RestaurantPageActivity.id

            detailInformationState.observe { state ->
                state.onSuccess {
                    adapter.submitList(
                        listOf(
                            RestaurantIntroductionModel.toPresentation(it),
                            RestaurantDetailInformationModel.toPresentation(it)
                        )
                    )
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
            scrimColor = ContextCompat.getColor(this@RestaurantPageActivity, R.color.surface)
            setAllContainerColors(
                ContextCompat.getColor(this@RestaurantPageActivity, R.color.surface)
            )
            startShapeAppearanceModel = ShapeAppearanceModel.builder().setAllCornerSizes(
                resources.getDimension(R.dimen.restaurant_transition_shape_model_radius)
            ).build()
            duration = resources.getInteger(R.integer.restaurant_page_anim_duration).toLong()
        }
        addMaterialSharedElementReturnTransition {
            addTarget(android.R.id.content)
            scrimColor = ContextCompat.getColor(this@RestaurantPageActivity, R.color.surface)
            setAllContainerColors(
                ContextCompat.getColor(this@RestaurantPageActivity, R.color.surface)
            )
            endShapeAppearanceModel = ShapeAppearanceModel.builder().setAllCornerSizes(
                resources.getDimension(R.dimen.restaurant_transition_shape_model_radius)
            ).build()
            duration = resources.getInteger(R.integer.restaurant_page_anim_duration).toLong()
        }
    }

    companion object {
        const val KEY_RESTAURANT_ID = "restaurantId"
        const val KEY_TRANSITION_NAME = "transitionName"
    }

}
