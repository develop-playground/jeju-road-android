package github.dev_playground.jeju_road.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import github.dev_playground.jeju_road.R
import github.dev_playground.jeju_road.databinding.ActivityMainBinding
import github.dev_playground.jeju_road.ui.list.RestaurantListFragment
import github.dev_playground.jeju_road.ui.loading.LoadingEventViewModel
import github.dev_playground.jeju_road.util.dataBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val binding by dataBinding(ActivityMainBinding::inflate)
    private val viewModel by viewModel<LoadingEventViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView_main, RestaurantListFragment.newInstance())
            .commit()

        viewModel.loadingState.observe(this) {
            binding.progressBarMain.visibility = if (it.loading) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }

}