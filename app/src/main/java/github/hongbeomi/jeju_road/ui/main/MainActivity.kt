package github.hongbeomi.jeju_road.ui.main

import android.os.Bundle
import android.view.View
import github.hongbeomi.jeju_road.R
import github.hongbeomi.jeju_road.databinding.ActivityMainBinding
import github.hongbeomi.jeju_road.ui.base.BaseActivity
import github.hongbeomi.jeju_road.ui.list.RestaurantListFragment
import github.hongbeomi.jeju_road.ui.loading.LoadingEventViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel by viewModel<LoadingEventViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView_main, RestaurantListFragment.newInstance())
            .commit()

        viewModel.loadingState.observe {
            binding.progressBarMain.visibility = if (it.loading) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }

}