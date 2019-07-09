package com.troshchii.reddit.domain

import com.troshchii.reddit.core.extensions.getLogTag
import com.troshchii.reddit.network.RedditService


class TopNewsUseCase(private val service: RedditService) : UseCase() {

    private val tag = getLogTag<TopNewsUseCase>()

    override fun execute(): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}