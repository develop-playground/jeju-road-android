package github.dev_playground.jeju_road.ui.main

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import github.dev_playground.jeju_road.R
import github.dev_playground.jeju_road.databinding.ActivityMainBinding
import github.dev_playground.jeju_road.ui.base.BaseActivity
import github.dev_playground.jeju_road.ui.list.RestaurantListFragment
import github.dev_playground.jeju_road.ui.loading.LoadingEventViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbarMain)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView_main, RestaurantListFragment.newInstance())
            .commit()
    }

}