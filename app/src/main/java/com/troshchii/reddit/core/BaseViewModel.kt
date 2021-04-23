package com.troshchii.reddit.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.troshchii.reddit.core.extensions.logI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel : ViewModel() {

    protected var tag: String = this::class.java.simpleName

    init {
        logI(tag, "init")
    }

    /**
     * Goal is mutability.
     * Avoid setting data from Fragment or Activity. They only should observe it.
     * Casts the [LiveData] to [MutableLiveData] and calls [MutableLiveData.postValue]
     */
    protected fun <T> LiveData<T>.postUpdate(value: T?) {
        (this as MutableLiveData).postValue(value)
    }

//    protected fun <T> StateFlow<T>.postUpdate(value: T?) {
//        (this as MutableStateFlow).postValue(value)
//    }

    protected fun <T> LiveData<Event<T>>.postEvent(value: T) {
        (this as MutableLiveData).postValue(Event(value))
    }

    override fun onCleared() {
        logI(tag, "onCleared")
    }
}