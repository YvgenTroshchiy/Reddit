package com.troshchii.reddit

import android.app.Application
import com.troshchii.reddit.core.utils.setStrictMode
import com.troshchii.reddit.dagger.AppComponent
import com.troshchii.reddit.dagger.DaggerAppComponent
import com.troshchii.reddit.di.allAppModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

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
    }
}