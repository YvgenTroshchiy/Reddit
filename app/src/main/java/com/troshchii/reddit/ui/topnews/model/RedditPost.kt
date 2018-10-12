package com.troshchii.reddit.ui.topnews.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize data class RedditPost(
    val thumbnail: String?,
    val imageUrl: String?,
    val title: String,
    val author: String,
    val created_utc: String,
    val num_comments: String
) : Parcelable