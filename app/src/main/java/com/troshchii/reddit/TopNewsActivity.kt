package com.troshchii.reddit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.troshchii.reddit.di.scope.ActivityScoped
import com.troshchii.reddit.extensions.getLogTag
import com.troshchii.reddit.extensions.logW
import com.troshchii.reddit.extensions.transformer
import com.troshchii.reddit.network.RedditService
import dagger.android.AndroidInjection
import javax.inject.Inject


@ActivityScoped
class TopNewsActivity : AppCompatActivity() {

    private val tag = getLogTag<TopNewsActivity>()

    @Inject lateinit var service: RedditService

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
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