package github.dev_playground.jeju_road.presentation.ui.main

import android.os.Bundle
import github.dev_playground.jeju_road.presentation.R
import github.dev_playground.jeju_road.presentation.databinding.ActivityMainBinding
import github.dev_playground.jeju_road.presentation.ui.base.BaseActivity
import github.dev_playground.jeju_road.presentation.ui.list.RestaurantListFragment

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