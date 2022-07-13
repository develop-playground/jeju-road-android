package github.dev_playground.jeju_road.presentation.ui.base

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import github.dev_playground.jeju_road.presentation.util.Event

abstract class BaseActivity<B: ViewDataBinding>(
    @LayoutRes layoutId: Int
): AppCompatActivity(layoutId) {

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

    protected infix fun <T> LiveData<T>.observe(action: (T) -> Unit) {
        observe(this@BaseActivity, action)
    }

    protected infix fun <T> LiveData<Event<T>>.eventObserve(action: (T) -> Unit) {
        observe(this@BaseActivity) { it.get(action) }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

}