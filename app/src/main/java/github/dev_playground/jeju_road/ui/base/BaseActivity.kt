package github.dev_playground.jeju_road.ui.base

import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.updateLayoutParams
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import github.dev_playground.jeju_road.domain.usecase.GetRestaurantDetailUseCase
import github.dev_playground.jeju_road.ui.loading.LoadingEventViewModel
import github.dev_playground.jeju_road.util.Event
import org.koin.android.viewmodel.ext.android.viewModel

abstract class BaseActivity<B: ViewDataBinding>(@LayoutRes layoutId: Int): AppCompatActivity(layoutId) {

    protected val binding: B by lazy { DataBindingUtil.setContentView<B>(this, layoutId) }

    protected val loadingEventViewModel by viewModel<LoadingEventViewModel>()
    private lateinit var loadingView: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding {
            lifecycleOwner = this@BaseActivity
        }
        setUpProgressBar()

        loadingEventViewModel.loadingState.observe {
            loadingView.visibility = if (it.loading) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }

    protected fun binding(action: B.() -> Unit) {
        binding.run(action)
    }

    private fun setUpProgressBar() {
        loadingView = FrameLayout(this).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )

            val progressBar = ProgressBar(this@BaseActivity).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                isIndeterminate = true
            }

            addView(progressBar)
            (progressBar.layoutParams as FrameLayout.LayoutParams).gravity = Gravity.CENTER
            visibility = View.GONE
        }
        val viewGroup = binding.root as ViewGroup
        viewGroup.addView(loadingView)
    }

    protected infix fun <T> LiveData<T>.observe(action: (T) -> Unit) {
        observe(this@BaseActivity, action)
    }

    protected infix fun <T> LiveData<Event<T>>.eventObserve(action: (T) -> Unit) {
        observe(this@BaseActivity, { it.get(action) })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

}