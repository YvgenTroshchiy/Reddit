package com.troshchii.reddit.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize data class TopNews(
    val data: Data
) : Parcelable

@Parcelize data class Data(
    val children: List<Children>,
    val after: String
) : Parcelable

@Parcelize data class Children(
    val kind: String,
    val data: News
) : Parcelable

@Parcelize data class News(
    val title: String,
    val author: String,
    val created_utc: String,
    val num_comments: String
) : Parcelable