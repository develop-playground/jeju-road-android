package github.dev_playground.jeju_road.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseCustomView<VB : ViewDataBinding>
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    @LayoutRes
    abstract fun getLayoutId(): Int

    protected val binding: VB by lazy {
        DataBindingUtil.inflate(
            LayoutInflater.from(context),
            getLayoutId(),
            this,
            true
        ) as VB
    }

    init {
        layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT,
        )
    }

}