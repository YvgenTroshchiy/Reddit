package com.troshchii.reddit

import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho
import com.troshchii.reddit.core.utils.setStrictMode
import com.troshchii.reddit.di.AppComponent
import com.troshchii.reddit.di.DaggerAppComponent

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> applicationContext.appComponent
    }

class App : Application() {

//    val appComponent: AppComponent by lazy {
//        DaggerAppComponent.factory().create(this)
//    }

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        if (BuildConfig.DEBUG) {
            setStrictMode()
            Stetho.initializeWithDefaults(this);
        }
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }
}
