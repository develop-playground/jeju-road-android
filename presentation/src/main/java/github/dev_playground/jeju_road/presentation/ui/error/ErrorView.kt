package github.dev_playground.jeju_road.presentation.ui.error

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import github.dev_playground.jeju_road.presentation.R
import github.dev_playground.jeju_road.presentation.databinding.CustomErrorViewBinding

class ErrorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
) : ConstraintLayout(context, attrs, defStyle) {
    private val binding: CustomErrorViewBinding by lazy {
        CustomErrorViewBinding.inflate(LayoutInflater.from(context), this, true)
    }
    private var refreshClickListener: (() -> Unit)? = null

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.ErrorView, defStyle, 0).apply {
            binding.textViewMessageCustomErrorView.text = getString(R.styleable.ErrorView_errorText)
            binding.buttonRetryCustomErrorView.setOnClickListener {
                refreshClickListener?.invoke()
            }
            recycle()
        }
    }

    fun setErrorMessage(errorMessage: String?) {
        setText(errorMessage)
    }

    fun setOnRefreshClickListener(action: () -> Unit) {
        refreshClickListener = action
    }

    private fun setText(text: String?) {
        if (text.isNullOrEmpty()) {
            binding.textViewMessageCustomErrorView.text =
                resources.getText(R.string.text_error_unException_message)
        } else {
            binding.textViewMessageCustomErrorView.text = text
        }
    }

}