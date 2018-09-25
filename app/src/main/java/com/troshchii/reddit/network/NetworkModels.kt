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
    val preview: Preview,
    val title: String,
    val author: String,
    val created_utc: String,
    val num_comments: String
) : Parcelable

@Parcelize data class Preview(
    val images: List<Image>,
    val enabled: Boolean
) : Parcelable

@Parcelize data class Image(
    val source: Source,
    val resolutions: List<Source>
) : Parcelable

@Parcelize data class Source(
    val url: String,
    val width: Int,
    val height: Int
) : Parcelable