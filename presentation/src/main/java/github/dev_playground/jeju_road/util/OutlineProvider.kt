package github.dev_playground.jeju_road.util

import android.graphics.Outline
import android.view.View
import android.view.ViewOutlineProvider
import androidx.annotation.DimenRes
import github.dev_playground.jeju_road.R

class RoundRectOutlineProvider(
    @DimenRes
    private val radius: Int = R.dimen.dp_8
): ViewOutlineProvider() {

    override fun getOutline(view: View?, outline: Outline?) {
        view?.apply {
            clipToOutline = true
            outline?.setRoundRect(
                paddingLeft,
                paddingTop,
                width - paddingRight,
                height - paddingLeft,
                resources.getDimension(radius)
            )
        }
    }

}