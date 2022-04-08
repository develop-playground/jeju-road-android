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

/**
 * color를 지정하지 않아서 투명색으로 사용될 경우
 * onDrawOver를 통해 아이템 간에 여백을 줄 수 없기에 getItemOffsets를 함께 사용한다.
 */
class RestaurantListItemDecoration(
    @DimenRes
    private val spaceResId: Int = R.dimen.dp_8,
    @ColorInt
    private val color: Int = Color.TRANSPARENT
): RecyclerView.ItemDecoration() {

    private val paint = Paint()

    init {
        paint.color = color
    }

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

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = 0f
        val right = parent.width.toFloat()
        val height  = parent.context.resources.getDimension(spaceResId)

        for(i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = (child.bottom + params.bottomMargin).toFloat()
            val bottom = top + height

            c.drawRect(left, top, right, bottom, paint)
        }
    }
}