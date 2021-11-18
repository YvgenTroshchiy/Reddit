package com.troshchii.reddit.dagger

import android.content.Context
import com.google.android.datatransport.runtime.dagger.BindsInstance
import com.google.android.datatransport.runtime.dagger.Component

@Component(modules = [NetworkModule::class])
interface AppComponent {

    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): AppComponent
    }

}
