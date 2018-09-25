package com.troshchii.reddit.di

import com.troshchii.reddit.network.RedditService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton


@Module(includes = [NetworkModule::class])
class RedditServiceModule {

    @Provides
    @Singleton
    fun service(retrofit: Retrofit): RedditService =
        retrofit.create<RedditService>(RedditService::class.java)

}