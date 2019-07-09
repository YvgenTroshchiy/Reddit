package com.troshchii.reddit.ui.topnews.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize data class TopNewsDto(
    val data: Data
) : Parcelable

@Parcelize data class Data(
    val children: List<Children>,
    val before: String?,
    val after: String?
) : Parcelable

@Parcelize data class Children(
    val kind: String,
    val data: ChildrenData
) : Parcelable

@Parcelize data class ChildrenData(
    val thumbnail: String,
    val preview: Preview?,
    val title: String,
    val author: String,
    val created_utc: String,
    val numComments: String
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