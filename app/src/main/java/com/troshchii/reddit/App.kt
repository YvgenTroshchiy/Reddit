package com.troshchii.reddit

import com.squareup.leakcanary.LeakCanary
import com.troshchii.reddit.core.extensions.setStrictMode
import com.troshchii.reddit.di.DaggerAppComponent
import com.troshchii.reddit.di.koin.appModule
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import org.koin.android.ext.android.startKoin


class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder().application(this).build()

    override fun onCreate() {
        if (BuildConfig.DEBUG) setStrictMode()
        super.onCreate()

        when {
            LeakCanary.isInAnalyzerProcess(this) -> return
            else -> LeakCanary.install(this)
        }


        startKoin(this, listOf(appModule))
    }
}