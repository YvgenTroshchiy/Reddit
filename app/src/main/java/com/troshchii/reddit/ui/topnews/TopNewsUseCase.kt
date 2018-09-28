package com.troshchii.reddit.ui.topnews

import com.troshchii.reddit.core.UseCase
import com.troshchii.reddit.extensions.getLogTag
import com.troshchii.reddit.extensions.logW
import com.troshchii.reddit.extensions.transformer
import com.troshchii.reddit.network.RedditService
import com.troshchii.reddit.network.TopNews
import io.reactivex.Single
import javax.inject.Inject


class TopNewsUseCase @Inject constructor(
    // TODO: Add Repository
    private val service: RedditService
) : UseCase() {

    private val tag = getLogTag<TopNewsUseCase>()

    override fun execute(): Single<TopNews> {
        logW(tag, "execute")

        return service.topNews(10)
            .compose { transformer(it) }
    }
}