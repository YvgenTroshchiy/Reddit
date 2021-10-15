package com.troshchii.reddit.ui.topnews.data

fun TopNewsDto.toTopNews() = listingData.children.map { childrenToRedditPost(it) }

private fun childrenToRedditPost(children: TopNewsDto.ListingData.Children): RedditPost {
    val redditPost = children.redditPost

    return RedditPost(
        id = redditPost.id,
        thumbnail = redditPost.media?.oembed?.thumbnailUrl ?: redditPost.thumbnail,
        imageUrl = redditPost.url,
        title = redditPost.title,
        author = redditPost.author,
        created_utc = redditPost.created_utc,
        numComments = redditPost.numComments,
        isVideo = redditPost.isVideo,
    )
}
