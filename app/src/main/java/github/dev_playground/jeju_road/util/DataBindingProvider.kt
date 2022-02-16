package github.dev_playground.jeju_road.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

internal class ActivityBindingProvider<out T : ViewDataBinding>(
    private val factory: (LayoutInflater) -> T
) : ReadOnlyProperty<AppCompatActivity, T>, LifecycleObserver {

    private var binding: T? = null

    @MainThread
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    override fun getValue(thisRef: AppCompatActivity, property: KProperty<*>): T {
        val value = binding ?: factory(thisRef.layoutInflater).apply {
            binding = this
        }
        thisRef.setContentView(value.root)
        return value
    }

}

internal class FragmentBindingProvider<out T : ViewDataBinding>(
    private val factory: (View) -> T
) : ReadOnlyProperty<Fragment, T> {
    private var binding: T? = null

    @MainThread
    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        return binding ?: this.factory(thisRef.requireView()).also {
            binding = it
        }
    }

}

internal fun <T : ViewDataBinding> AppCompatActivity.dataBinding(
    factory: (LayoutInflater) -> T
) = ActivityBindingProvider(factory)

internal fun <T : ViewDataBinding> Fragment.dataBinding(
    factory: (View) -> T
) = FragmentBindingProvider(factory)

@MainThread
inline fun <T : ViewDataBinding> DialogFragment.dataBinding(crossinline factory: (LayoutInflater) -> T) =
    lazy { factory(layoutInflater) }


@MainThread
internal inline fun <T : ViewDataBinding> ViewGroup.dataBinding(
    factory: (LayoutInflater, ViewGroup, Boolean) -> T
) = factory(LayoutInflater.from(context), this, false)