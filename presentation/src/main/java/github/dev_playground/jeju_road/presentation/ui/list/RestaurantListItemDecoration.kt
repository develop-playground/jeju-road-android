package github.dev_playground.jeju_road.presentation.ui.list

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.DimenRes
import androidx.core.view.marginBottom
import androidx.recyclerview.widget.RecyclerView
import github.dev_playground.jeju_road.presentation.R
import kotlinx.coroutines.handleCoroutineException

class RestaurantListItemDecoration(
    @DimenRes
    private val spaceResId: Int = R.dimen.dp_8
): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val space = view.context.resources.getDimensionPixelSize(spaceResId)

        if (position != 0) {
            outRect.top = space
        }
    }
}