package com.troshchii.reddit.core.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import com.troshchii.reddit.core.exception.Failure

inline fun <reified VM : ViewModel> AppCompatActivity.viewModel(
    factory: ViewModelProvider.Factory
): VM = ViewModelProviders.of(this, factory)[VM::class.java]

inline fun <reified VM : ViewModel> AppCompatActivity.withViewModel(
    factory: ViewModelProvider.Factory, body: VM.() -> Unit
): VM = viewModel<VM>(factory).apply { body() }

fun <T> LiveData<T>.startWith(value: T): LiveData<T> {
    val mediator = MediatorLiveData<T>()
    mediator.addSource(this) { mediator.value = it }
    return mediator.apply { this.value = value }
}

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) =
    liveData.observe(this, Observer(body))

fun <L : LiveData<Failure>> LifecycleOwner.failure(liveData: L, body: (Failure?) -> Unit) =
    liveData.observe(this, Observer(body))
