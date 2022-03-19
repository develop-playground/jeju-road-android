package github.dev_playground.jeju_road.presentation.ui.component

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import androidx.core.widget.TextViewCompat
import github.dev_playground.jeju_road.presentation.R

class RestaurantCategoryView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : androidx.appcompat.widget.AppCompatTextView(context, attrs) {

    @ColorRes
    private val backgroundColor = R.color.blue_light
    @DimenRes
    private val paddingSize = R.dimen.padding_restaurant_category_view

    init {
        setBackgroundColor(
            ContextCompat.getColor(context, backgroundColor)
        )
        TextViewCompat.setTextAppearance(this, R.style.Text_BODY_RESTAURANT_PATE)
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)
        setPadding(resources.getDimensionPixelSize(paddingSize))
    }

}