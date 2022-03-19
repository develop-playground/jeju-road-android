package github.dev_playground.jeju_road.presentation.ui.list

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import github.dev_playground.jeju_road.presentation.R

class RestaurantListItemDecoration: RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val space = view.context.resources.getDimensionPixelSize(R.dimen.dp_8)

        if (position != 0) {
            outRect.top = space
        }
    }

}