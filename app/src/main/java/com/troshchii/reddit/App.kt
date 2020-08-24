package com.troshchii.reddit

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.troshchii.reddit.core.utils.setStrictMode
import com.troshchii.reddit.di.allAppModules
import io.fabric.sdk.android.Fabric
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class App : Application() {

    override fun onCreate() {
        if (BuildConfig.DEBUG) setStrictMode()
        super.onCreate()

        startKoin {
            androidContext(this@App)
            // cause crash "java.lang.NoSuchMethodError: No virtual method elapsedNow()D in class Lkotlin/time/TimeMark;"
            // https://stackoverflow.com/a/63393508/3825816
            // androidLogger()
            modules(allAppModules)
        }

        Fabric.with(this, Crashlytics())
    }
}