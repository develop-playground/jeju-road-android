package github.hongbeomi.jeju_road

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import github.hongbeomi.jeju_road.domain.usecase.GetRestaurantListUseCase
import github.hongbeomi.jeju_road.util.data
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.experimental.property.inject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}