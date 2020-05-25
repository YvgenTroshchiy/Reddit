package com.troshchii.reddit.ui.topnews

import com.troshchii.reddit.core.exception.Failure
import com.troshchii.reddit.core.extensions.getLogTag
import com.troshchii.reddit.core.extensions.logI
import com.troshchii.reddit.core.functional.Either
import com.troshchii.reddit.domain.LIMIT
import com.troshchii.reddit.domain.TopNewsUseCase
import com.troshchii.reddit.network.RedditService
import com.troshchii.reddit.ui.topnews.data.RedditPost
import com.troshchii.reddit.ui.topnews.data.toTopNews


class TopNewsRepository(
//    private val topNewsUseCase: TopNewsUseCase,
    private val service: RedditService
) {

    private val tag = getLogTag<TopNewsUseCase>()

    private var after: String? = null

    suspend fun initialLoad(): Either<Failure, List<RedditPost>> {
        logI(tag, "initialLoad")
        return loadTopNews()
    }

    suspend fun loadMore(): Either<Failure, List<RedditPost>> {
        logI(tag, "loadMore")
        return loadTopNews(after)
    }

    private suspend fun loadTopNews(after: String? = null): Either<Failure, List<RedditPost>> {
        logI(tag, "loadTopNews")

        val result = service.topNews(LIMIT, after)

        return if (result.isSuccessful && result.body() != null) {
            this.after = result.body()!!.listingData.after

            Either.Right(result.body()!!.toTopNews())
        } else {
            Either.Left(Failure.ServerError(message = result.message()))
        }
    }
}