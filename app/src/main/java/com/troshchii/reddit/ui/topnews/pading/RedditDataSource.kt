package com.troshchii.reddit.ui.topnews.pading

import androidx.paging.PageKeyedDataSource
import com.troshchii.reddit.core.extensions.getLogTag
import com.troshchii.reddit.core.extensions.logE
import com.troshchii.reddit.network.RedditService
import com.troshchii.reddit.ui.topnews.PAGE_SIZE
import com.troshchii.reddit.ui.topnews.data.RedditPost
import com.troshchii.reddit.ui.topnews.data.toTopNews
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


class RedditDataSource(
    private val scope: CoroutineScope,
    private val service: RedditService
) : PageKeyedDataSource<String, RedditPost>() {

    private val tag = getLogTag<RedditDataSource>()

    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, RedditPost>
    ) {
        scope.launch {
            try {
                val body = service.topNews(PAGE_SIZE).execute().body()
                val data = body?.data
                val items = body?.toTopNews() ?: emptyList()

                callback.onResult(items, data?.before, data?.after)
            } catch (e: Exception) {
                logE(tag, "loadInitial", e)
//                e.message ?: "unknown error"
            }
        }
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, RedditPost>) {
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, RedditPost>) {}
}