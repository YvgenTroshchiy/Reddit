package com.troshchii.reddit.extensions

import android.content.Context
import android.os.StrictMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*


fun setStrictMode() {
    StrictMode.setThreadPolicy(
        StrictMode.ThreadPolicy.Builder()
            .detectAll()
            .penaltyLog()
            .build()
    )

    StrictMode.setVmPolicy(
        StrictMode.VmPolicy.Builder()
            .detectLeakedSqlLiteObjects()
            .penaltyLog()
            .penaltyDeath()
            .build()
    )
}

fun Context.inflater(): LayoutInflater = LayoutInflater.from(this)

fun ViewGroup.inflate(
    layoutId: Int, attachToRoot: Boolean = false
): View = LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)

inline fun <reified VM : ViewModel> AppCompatActivity.viewModel(
    factory: ViewModelProvider.Factory
): VM = ViewModelProviders.of(this, factory)[VM::class.java]

inline fun <reified VM : ViewModel> AppCompatActivity.withViewModel(
    factory: ViewModelProvider.Factory, body: VM.() -> Unit
): VM = viewModel<VM>(factory).apply { body() }

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(
    liveData: L, body: (T?) -> Unit
) = liveData.observe(this, Observer(body))