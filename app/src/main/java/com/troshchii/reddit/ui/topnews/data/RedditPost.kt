package com.troshchii.reddit.ui.topnews.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize data class RedditPost(
    val id: String,
    val thumbnail: String?,
    val imageUrl: String,
    val title: String,
    val author: String,
    val created_utc: String,
    val numComments: String,
    val isVideo: Boolean,
    val videoUrl: String?,
    val fallbackVideoUrl: String?,
) : Parcelable
