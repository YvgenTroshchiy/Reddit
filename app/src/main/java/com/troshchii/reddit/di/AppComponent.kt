package com.troshchii.reddit.di

import android.app.Application
import com.troshchii.reddit.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AppModule::class,
        ViewModelModule::class,
        RedditServiceModule::class,
        ActivityBindingModule::class,
        AndroidSupportInjectionModule::class]
)
interface AppComponent : AndroidInjector<App> {

    // Gives us syntactic sugar. we can then do DaggerAppComponent.builder().application(this).build().inject(this);
    // never having to instantiate any modules or say which module we are passing the application to.
    // Application will just be provided into our app graph now.
    @Component.Builder interface Builder {

        @BindsInstance fun application(application: Application): Builder

        fun build(): AppComponent
    }
}