package com.troshchii.reddit

import com.squareup.leakcanary.LeakCanary
import com.troshchii.reddit.di.DaggerAppComponent
import com.troshchii.reddit.extensions.setStrictMode
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication


class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder().application(this).build()

    override fun onCreate() {
//        if (BuildConfig.DEBUG) setStrictMode()
        super.onCreate()

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)
    }

}