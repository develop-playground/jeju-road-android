package github.hongbeomi.jeju_road.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<B: ViewDataBinding>(@LayoutRes layoutId: Int): AppCompatActivity(layoutId) {

    protected val binding: B by lazy { DataBindingUtil.setContentView<B>(this, layoutId) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding {
            lifecycleOwner = this@BaseActivity
        }
    }

    protected fun binding(action: B.() -> Unit) {
        binding.run(action)
    }

}