package github.dev_playground.jeju_road.presentation.ui.component

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import androidx.annotation.DimenRes
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import androidx.core.widget.TextViewCompat
import github.dev_playground.jeju_road.presentation.R
import github.dev_playground.jeju_road.presentation.util.RoundRectOutlineProvider

class RestaurantCategoryView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : androidx.appcompat.widget.AppCompatTextView(context, attrs) {

    @DimenRes
    private val paddingSize = R.dimen.restaurant_category_padding

    init {
        TextViewCompat.setTextAppearance(this, R.style.JejuLoadTextStyle)
        setTextColor(ContextCompat.getColor(context, R.color.onPrimary))
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)
        setPadding(resources.getDimensionPixelSize(paddingSize))
        background = ContextCompat.getDrawable(context, R.drawable.bg_restaurant_category_view)
        outlineProvider = RoundRectOutlineProvider(R.dimen.restaurant_category_radius)
    }

}