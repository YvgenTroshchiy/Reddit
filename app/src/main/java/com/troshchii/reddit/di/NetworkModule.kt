package com.troshchii.reddit.di

import android.content.Context
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.troshchii.reddit.BuildConfig
import com.troshchii.reddit.BuildConfig.DEBUG
import com.troshchii.reddit.network.RedditService
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit.SECONDS


private const val TIMEOUT = 30L


val networkModule = module {
    single { redditService(get()) }

    single { retrofit(get(), get()) }

    single { okHttpClient(get()) }
    single { cache(get()) }
    single { cacheFile(androidContext()) }

    single { gson() }
}

fun redditService(retrofit: Retrofit): RedditService = retrofit.create(RedditService::class.java)

fun retrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit =
    Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

fun okHttpClient(cache: Cache): OkHttpClient =
    OkHttpClient.Builder()
        .connectTimeout(TIMEOUT, SECONDS)
        .readTimeout(TIMEOUT, SECONDS)
        .writeTimeout(TIMEOUT, SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply { level = if (DEBUG) BODY else NONE })
        .cache(cache)
        .build()


fun cache(cacheFile: File) = Cache(cacheFile, (10 * 1024 * 1024 /* 10MB Cache */).toLong())

fun cacheFile(context: Context): File = File(context.cacheDir, "okhttp_cache")

fun gson(): Gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()