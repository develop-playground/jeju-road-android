package github.dev_playground.jeju_road.util

import android.graphics.Outline
import android.view.View
import android.view.ViewOutlineProvider

class RoundRectOutlineProvider(private val radius: Float): ViewOutlineProvider() {

    override fun getOutline(view: View?, outline: Outline?) {
        view?.apply {
            clipToOutline = true
            outline?.setRoundRect(
                paddingLeft,
                paddingTop,
                width - paddingRight,
                height - paddingLeft,
                radius
            )
        }
    }

}