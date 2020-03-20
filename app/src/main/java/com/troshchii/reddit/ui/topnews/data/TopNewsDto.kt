package com.troshchii.reddit.ui.topnews.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize data class TopNewsDto(
    @SerializedName("data") val listingData: ListingData
) : Parcelable {

    @Parcelize data class ListingData(
        val modhash: String,
        val dist: Int,
        val children: List<Children>,
        val after: String?,
        val before: String?
    ) : Parcelable {

        @Parcelize data class Children(
            val kind: String,
            @SerializedName("data") val redditPost: RedditPost
        ) : Parcelable {

            @Parcelize data class RedditPost(
                val id: String,
                val thumbnail: String,
                val preview: Preview?,
                val title: String,
                val author: String,
                val created_utc: String,
                val numComments: String,
                val media: Media?
            ) : Parcelable {

                @Parcelize data class Preview(
                    val images: List<Image>,
                    val redditVideoPreview: RedditVideoPreview,
                    val enabled: Boolean
                ) : Parcelable {

                    @Parcelize data class Image(
                        val source: Source,
                        val resolutions: List<Source>
                    ) : Parcelable {

                        @Parcelize data class Source(
                            val url: String,
                            val width: Int,
                            val height: Int
                        ) : Parcelable
                    }

                    @Parcelize data class RedditVideoPreview(
                        val fallback_url: String,
                        val height: Int,
                        val width: Int,
                        val scrubber_media_url: String,
                        val dash_url: String,
                        val duration: Int,
                        val hls_url: String,
                        val is_gif: Boolean,
                        val transcoding_status: String
                    ) : Parcelable
                }

                @Parcelize data class Media(
                    val oembed: Oembed,
                    val type: String
                ) : Parcelable {

                    @Parcelize data class Oembed(
                        val providerUrl: String,
                        val description: String,
                        val title: String,
                        val authorName: String,
                        val height: Int,
                        val width: Int,
                        val html: String,
                        val thumbnailWidth: Int,
                        val version: String,
                        val providerName: String,
                        val thumbnailUrl: String,
                        val type: String,
                        val thumbnailHeight: Int
                    ) : Parcelable
                }
            }
        }
    }
}