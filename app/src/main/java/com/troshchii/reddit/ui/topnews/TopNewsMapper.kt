package com.troshchii.reddit.ui.topnews

import com.troshchii.reddit.network.Children
import com.troshchii.reddit.network.TopNews
import javax.inject.Inject


class TopNewsMapper @Inject constructor() {

    fun computeTopNews(topNews: TopNews): List<News> {
        return topNews.data.children.map { childrenToNews(it) }
    }

    private fun childrenToNews(children: Children): News {
        val data = children.data

        return News(
            // TODO: Get from resolutions
            thumbnail = data.preview?.images?.get(0)?.source?.url,
            imageUrl = data.preview?.images?.get(0)?.source?.url,
            title = data.title,
            author = data.author,
            created_utc = data.created_utc,
            num_comments = data.num_comments
        )
    }
}