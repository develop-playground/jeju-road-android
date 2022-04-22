package github.dev_playground.jeju_road.presentation.util

import android.graphics.Outline
import android.view.View
import android.view.ViewOutlineProvider
import androidx.annotation.DimenRes
import github.dev_playground.jeju_road.presentation.R

class RoundRectOutlineProvider(
    @DimenRes
    private val radius: Int = R.dimen.round_rect_outline_provider_default_radius
): ViewOutlineProvider() {

    override fun getOutline(view: View?, outline: Outline?) {
        view?.apply {
            clipToOutline = true
            outline?.setRoundRect(
                0,
                0,
                width,
                height,
                resources.getDimension(radius)
            )
        }
    }

}