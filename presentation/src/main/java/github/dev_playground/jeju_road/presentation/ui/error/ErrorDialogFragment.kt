package github.dev_playground.jeju_road.presentation.ui.error

import android.graphics.Point
import android.os.Bundle
import android.view.*
import github.dev_playground.jeju_road.presentation.R
import github.dev_playground.jeju_road.presentation.databinding.ActivityRestaurantDetailBinding
import github.dev_playground.jeju_road.presentation.databinding.FragmentDialogErrorBinding
import github.dev_playground.jeju_road.presentation.ui.base.BaseDialogFragment

class ErrorDialogFragment : BaseDialogFragment<FragmentDialogErrorBinding>(R.layout.fragment_dialog_error) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onResume() {
        val window: Window? = dialog?.window

        if (window != null) {
            val display: Display = window.windowManager.defaultDisplay
            val size = Point()
            display.getSize(size)

            val v: View = window.decorView
            v.setBackgroundResource(R.drawable.bg_error_dialog_radius_shape)

            val params: ViewGroup.LayoutParams? = window.attributes
            val deviceWidth = size.x
            val deviceHeight = size.y

            params?.width = (deviceWidth * 0.8).toInt()
            params?.height = (deviceHeight * 0.48).toInt()

            window.attributes = params as WindowManager.LayoutParams

            super.onResume()
        }
    }
}