package com.troshchii.reddit.ui.topnews

import com.troshchii.reddit.core.UseCase
import com.troshchii.reddit.core.extensions.getLogTag
import com.troshchii.reddit.core.extensions.logI
import com.troshchii.reddit.core.extensions.transformer
import com.troshchii.reddit.network.RedditService
import com.troshchii.reddit.ui.topnews.model.TopNewsDto
import io.reactivex.Single


class TopNewsUseCase(private val service: RedditService) : UseCase() {

    private val tag = getLogTag<TopNewsUseCase>()

    override fun execute(): Single<TopNewsDto> {
        logI(tag, "execute")

        return service.topNews(100).compose { transformer(it) }
    }
}