package com.troshchii.reddit.ui.topnews.data


fun TopNewsDto.toTopNews() = data.children.map { childrenToRedditPost(it) }


private fun childrenToRedditPost(children: TopNewsDto.ListingData.Children): RedditPost {
    val data = children.data

    return RedditPost(
        id = data.id,
        // TODO: Get from resolutions
        thumbnail = data.thumbnail,
        imageUrl = data.preview?.images?.get(0)?.source?.url,
        title = data.title,
        author = data.author,
        created_utc = data.created_utc,
        numComments = data.numComments
    )
}