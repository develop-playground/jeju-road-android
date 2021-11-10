package github.dev_playground.jeju_road.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData

class EventLiveData<T> : MutableLiveData<Event<T>>() {

    fun setEventValue(value: T) {
        super.setValue(Event(value))
    }

    fun observe(owner: LifecycleOwner, onResult: (T) -> Unit) {
        super.observe(owner, {
            it.get(onResult)
        })
    }

}