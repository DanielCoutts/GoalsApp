package com.danielcoutts.goalsapp.util

import androidx.lifecycle.*

/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 * (See https://medium.com/androiddevelopers/livedata-with-snackbar-navigation-and-other-events-the-singleliveevent-case-ac2622673150)
 */
data class Event<out T>(private val content: T) {

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

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}

fun <T> MutableLiveData<Event<T>>.postEvent(event: T) = postValue(Event(event))
fun <T> LiveData<Event<T>>.observeEvents(owner: LifecycleOwner, onChanged: (t: T) -> Unit) = observe(owner) {
    it.getContentIfNotHandled()?.let { t -> onChanged(t) }
}