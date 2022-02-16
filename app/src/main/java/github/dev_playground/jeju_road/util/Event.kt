package github.dev_playground.jeju_road.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

open class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    fun get(action: (T) -> Unit) {
        getContentIfNotHandled()?.let(action)
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content

}

fun <T> LiveData<Event<T>>.eventObserve(lifecycleOwner: LifecycleOwner, action: (T) -> Unit) {
    observe(lifecycleOwner, { it.get(action) })
}