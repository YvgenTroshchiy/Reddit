package com.troshchii.reddit.ui.topnews.model


fun TopNewsDto.toTopNews() = data.children.map { childrenToRedditPost(it) }

private fun childrenToRedditPost(children: Children): RedditPost {
    val data = children.data

    return RedditPost(
        // TODO: Get from resolutions
        thumbnail = data.thumbnail,
        imageUrl = data.preview?.images?.get(0)?.source?.url,
        title = data.title,
        author = data.author,
        created_utc = data.created_utc,
        num_comments = data.num_comments
    )
}