package com.troshchii.reddit.ui.topnews

import com.troshchii.reddit.core.exception.Failure
import com.troshchii.reddit.core.functional.Either
import com.troshchii.reddit.domain.TopNewsUseCase
import com.troshchii.reddit.ui.topnews.data.RedditPost


class TopNewsRepository(private val topNewsUseCase: TopNewsUseCase) {

    private var after: String? = null

    suspend fun loadTopNews(): Either<Failure, List<RedditPost>> {
        //TODO: update after
        return topNewsUseCase.execute(after)
    }
}