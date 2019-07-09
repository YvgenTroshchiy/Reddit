package com.troshchii.reddit.domain

import com.troshchii.reddit.core.exception.Failure
import com.troshchii.reddit.core.extensions.getLogTag
import com.troshchii.reddit.core.extensions.logI
import com.troshchii.reddit.core.functional.Either
import com.troshchii.reddit.network.RedditService
import com.troshchii.reddit.ui.topnews.data.RedditPost
import com.troshchii.reddit.ui.topnews.data.toTopNews


class TopNewsUseCase(private val service: RedditService) : UseCase() {

    private val tag = getLogTag<TopNewsUseCase>()

    override suspend fun execute(): Either<Failure, List<RedditPost>> {
        logI(tag, "execute")

        val result = service.topNews(100)

        return if (result.isSuccessful && result.body() != null) {
            Either.Right(result.body()!!.toTopNews())
        } else {
            Either.Left(Failure.ServerError(message = result.message()))
        }
    }
}