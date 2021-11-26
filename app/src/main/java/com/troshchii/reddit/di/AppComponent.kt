package com.troshchii.reddit.di

import android.content.Context
import com.google.android.datatransport.runtime.dagger.BindsInstance
import com.google.android.datatransport.runtime.dagger.Component
import com.troshchii.reddit.ui.topnews.TopNewsFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent {

    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(topNewsFragment: TopNewsFragment)

}
