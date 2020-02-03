package com.danielcoutts.goalsapp.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

fun <A, B, C> combineLatest(
        first: LiveData<A>,
        second: LiveData<B>,
        combineFunction: (A, B) -> C
): LiveData<C> = MediatorLiveData<C>().apply {
    addSource(first) { a ->
        second.value?.let { b ->
            value = combineFunction(a, b)
        }
    }
    addSource(second) { b ->
        first.value?.let { a ->
            value = combineFunction(a, b)
        }
    }
}