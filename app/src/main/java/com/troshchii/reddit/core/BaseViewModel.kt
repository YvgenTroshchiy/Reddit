package com.troshchii.reddit.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.troshchii.reddit.core.extensions.logI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext


abstract class BaseViewModel(
    private val uiContext: CoroutineContext = Dispatchers.Main
) : ViewModel(), CoroutineScope {

    protected var tag: String = this::class.java.simpleName

    override val coroutineContext: CoroutineContext
        get() = uiContext + SupervisorJob()

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

    override fun onCleared() {
        logI(tag, "onCleared")
        coroutineContext.cancel()
    }
}