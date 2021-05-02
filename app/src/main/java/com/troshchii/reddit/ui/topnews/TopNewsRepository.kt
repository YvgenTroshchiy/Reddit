package com.troshchii.reddit.ui.topnews

import com.troshchii.reddit.core.NetworkResult
import com.troshchii.reddit.core.extensions.getLogTag
import com.troshchii.reddit.core.extensions.logI
import com.troshchii.reddit.domain.LIMIT
import com.troshchii.reddit.network.RedditService
import com.troshchii.reddit.ui.topnews.data.RedditPost
import com.troshchii.reddit.ui.topnews.data.toTopNews
import java.util.LinkedList

class TopNewsRepository(
//    private val topNewsUseCase: TopNewsUseCase,
    private val service: RedditService
) {

    private val tag = getLogTag<TopNewsRepository>()

    // It's kinda DataSource
    private val topNewsList: LinkedList<RedditPost> = LinkedList()

    private var after: String? = null

    suspend fun initialLoad(): NetworkResult<List<RedditPost>> {
        logI(tag, "initialLoad")
        return loadTopNews()
    }

    suspend fun loadMore(): NetworkResult<List<RedditPost>> {
        logI(tag, "loadMore")
        return loadTopNews(after)
    }

    private suspend fun loadTopNews(after: String? = null): NetworkResult<List<RedditPost>> {
        logI(tag, "loadTopNews")

        val result = service.topNews(LIMIT, after)

        return if (result.isSuccessful && result.body() != null) {
            this.after = result.body()!!.listingData.after

            val right = result.body()!!.toTopNews()
            topNewsList.addAll(right)

            NetworkResult.Success(topNewsList)
        } else {
            NetworkResult.Error(Throwable(result.message()))
        }
    }
}