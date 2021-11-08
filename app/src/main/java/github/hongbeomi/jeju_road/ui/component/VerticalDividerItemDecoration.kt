package github.hongbeomi.jeju_road.ui.component

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView
import github.hongbeomi.jeju_road.R

class VerticalDividerItemDecoration(
    context: Context,
    private val height: Float = context.resources.getDimension(R.dimen.dp_1),
    private val horizontalPadding: Float = context.resources.getDimension(R.dimen.dp_16),
    @ColorInt
    private val color: Int = Color.LTGRAY
) : RecyclerView.ItemDecoration() {

    private val paint = Paint()

    init {
        paint.color = color
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingStart + horizontalPadding
        val right = parent.width - parent.paddingEnd - horizontalPadding

        (0 until parent.childCount).forEach {
            val child = parent.getChildAt(it)
            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = (child.bottom + params.bottomMargin).toFloat()
            val bottom = top + height

            c.drawRect(left, top, right, bottom, paint)
        }
    }

}