package com.troshchii.reddit.di

import android.content.Context
import com.troshchii.reddit.ui.topnews.TopNewsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(topNewsFragment: TopNewsFragment)

}
