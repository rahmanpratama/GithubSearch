package io.github.rahman.githubsearch.helper

import androidx.annotation.Keep
import androidx.lifecycle.Observer

@Keep
internal class EventObserver<T>(private val onEventUnhandledContent: (EventData<T>) -> Unit) :
    Observer<EventData<T>> {

    override fun onChanged(value: EventData<T>) {
        value?.getContentIfNotHandled()?.let {
            if (it) {
                onEventUnhandledContent(value)
            }
        }
    }
}