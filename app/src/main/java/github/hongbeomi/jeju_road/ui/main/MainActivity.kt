package github.hongbeomi.jeju_road.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import github.hongbeomi.jeju_road.R
import github.hongbeomi.jeju_road.databinding.ActivityMainBinding
import github.hongbeomi.jeju_road.ui.base.BaseActivity
import github.hongbeomi.jeju_road.ui.list.RestaurantListFragment

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView_main, RestaurantListFragment())
            .commit()
    }

}