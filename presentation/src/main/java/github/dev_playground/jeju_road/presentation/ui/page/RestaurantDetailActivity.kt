package github.dev_playground.jeju_road.presentation.ui.page

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.shape.ShapeAppearanceModel
import github.dev_playground.jeju_road.presentation.R
import github.dev_playground.jeju_road.presentation.databinding.ActivityRestaurantDetailBinding
import github.dev_playground.jeju_road.presentation.model.RestaurantDetailInformationModel
import github.dev_playground.jeju_road.presentation.model.RestaurantIntroductionModel
import github.dev_playground.jeju_road.presentation.ui.base.BaseActivity
import github.dev_playground.jeju_road.presentation.ui.list.RestaurantListItemDecoration
import github.dev_playground.jeju_road.presentation.util.addEnterMaterialSharedElementCallback
import github.dev_playground.jeju_road.presentation.util.addMaterialSharedElementEnterTransition
import github.dev_playground.jeju_road.presentation.util.addMaterialSharedElementReturnTransition
import github.dev_playground.jeju_road.presentation.util.onSuccess
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class RestaurantDetailActivity : BaseActivity<ActivityRestaurantDetailBinding>(
    R.layout.activity_restaurant_detail
) {

    private val viewModel by viewModel<RestaurantDetailViewModel> {
        parametersOf(intent.getLongExtra(KEY_RESTAURANT_ID, 0))
    }
    private val adapter: RestaurantDetailListAdapter by lazy { RestaurantDetailListAdapter() }

    private val transitionName: String? by lazy { intent.getStringExtra(KEY_TRANSITION_NAME) }

    override fun onCreate(savedInstanceState: Bundle?) {
        makeTransition()
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbarRestaurantDetail)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        binding {
            toolbarRestaurantDetail.setNavigationOnClickListener {
                finishAfterTransition()
            }
            recyclerViewRestaurantDetail.adapter = adapter
            recyclerViewRestaurantDetail.addItemDecoration(
                RestaurantListItemDecoration(
                    spaceResId = R.dimen.restaurant_detail_list_item_space,
                    color = ContextCompat.getColor(this@RestaurantDetailActivity, R.color.lightGray)
                )
            )
        }

        viewModel.detailInformationState.observe { state ->
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

    private fun makeTransition() {
        transitionName ?: return

        findViewById<View>(android.R.id.content).transitionName = transitionName
        addEnterMaterialSharedElementCallback()

        addMaterialSharedElementEnterTransition {
            addTarget(android.R.id.content)
            scrimColor = ContextCompat.getColor(this@RestaurantDetailActivity, R.color.surface)
            setAllContainerColors(
                ContextCompat.getColor(this@RestaurantDetailActivity, R.color.surface)
            )
            startShapeAppearanceModel = ShapeAppearanceModel.builder().setAllCornerSizes(
                resources.getDimension(R.dimen.restaurant_transition_shape_model_radius)
            ).build()
            duration = resources.getInteger(R.integer.restaurant_page_anim_duration).toLong()
        }
        addMaterialSharedElementReturnTransition {
            addTarget(android.R.id.content)
            scrimColor = ContextCompat.getColor(this@RestaurantDetailActivity, R.color.surface)
            setAllContainerColors(
                ContextCompat.getColor(this@RestaurantDetailActivity, R.color.surface)
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
