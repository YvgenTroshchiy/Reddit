package com.troshchii.reddit

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import com.troshchii.reddit.core.extensions.setStrictMode
import com.troshchii.reddit.di.koin.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class App : Application() {

    override fun onCreate() {
        if (BuildConfig.DEBUG) setStrictMode()
        super.onCreate()

        when {
            LeakCanary.isInAnalyzerProcess(this) -> return
            else -> LeakCanary.install(this)
        }

        startKoin {
            androidContext(this@App)
            androidLogger()
            modules(appModule)
        }
    }
}