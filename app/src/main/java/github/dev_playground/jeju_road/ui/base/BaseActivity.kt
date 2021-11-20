package github.dev_playground.jeju_road.ui.base

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import github.dev_playground.jeju_road.ui.loading.LoadingEventViewModel
import github.dev_playground.jeju_road.util.Event
import org.koin.android.viewmodel.ext.android.viewModel

abstract class BaseActivity<B: ViewDataBinding>(@LayoutRes layoutId: Int): AppCompatActivity(layoutId) {

    protected val binding: B by lazy { DataBindingUtil.setContentView<B>(this, layoutId) }

    private val loadingEventViewModel by viewModel<LoadingEventViewModel>()
    abstract val loadingProgressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding {
            lifecycleOwner = this@BaseActivity
        }
        loadingEventViewModel.loadingState.observe {
            loadingProgressBar.visibility = if (it.loading) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }

    protected fun binding(action: B.() -> Unit) {
        binding.run(action)
    }

    protected infix fun <T> LiveData<T>.observe(action: (T) -> Unit) {
        observe(this@BaseActivity, action)
    }

    protected infix fun <T> LiveData<Event<T>>.eventObserve(action: (T) -> Unit) {
        observe(this@BaseActivity, { it.get(action) })
    }

}