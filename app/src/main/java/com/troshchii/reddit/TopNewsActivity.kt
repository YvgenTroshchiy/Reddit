package com.troshchii.reddit

import android.os.Bundle
import com.troshchii.reddit.di.scope.ActivityScoped
import com.troshchii.reddit.extensions.getLogTag
import com.troshchii.reddit.extensions.logW
import com.troshchii.reddit.extensions.transformer
import com.troshchii.reddit.network.RedditService
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


@ActivityScoped
class TopNewsActivity : DaggerAppCompatActivity() {

    private val tag = getLogTag<TopNewsActivity>()

    @Inject lateinit var service: RedditService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.topnews_activity)

        service.topNews(10)
            .compose { transformer(it) }
            .subscribe({
                //TODO: debug
                val topNews = it
            }, {
                logW(tag, it)
            })
    }
}