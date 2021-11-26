package com.troshchii.reddit

import android.app.Application
import android.content.Context
import com.troshchii.reddit.core.utils.setStrictMode
import com.troshchii.reddit.di.AppComponent

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> applicationContext.appComponent
    }

class App : Application() {

//    val appComponent: AppComponent by lazy {
//        DaggerAppComponent.factory().create(this)
//    }

    override fun onCreate() {
//        if (BuildConfig.DEBUG) setStrictMode()
        super.onCreate()
    }
}
