package github.dev_playground.jeju_road.presentation.ui.error

import android.app.Dialog
import android.graphics.Point
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import github.dev_playground.jeju_road.presentation.R
import github.dev_playground.jeju_road.presentation.databinding.ActivityRestaurantDetailBinding
import github.dev_playground.jeju_road.presentation.databinding.FragmentDialogErrorBinding
import github.dev_playground.jeju_road.presentation.ui.base.BaseDialogFragment

class ErrorDialogFragment
    : BaseDialogFragment<FragmentDialogErrorBinding>(R.layout.fragment_dialog_error) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.setCanceledOnTouchOutside(false)
        binding {
            imageViewCloseErrorDialog.setOnClickListener {
                dialog?.dismiss()
            }

            buttonRetryErrorDialog.setOnClickListener {
                println("부모: ${parentFragment}, 자식 ${childFragmentManager}")
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        setStyle(STYLE_NO_TITLE, R.style.Dialog_ERROR_DIALOG)
        return super.onCreateDialog(savedInstanceState)
    }
}