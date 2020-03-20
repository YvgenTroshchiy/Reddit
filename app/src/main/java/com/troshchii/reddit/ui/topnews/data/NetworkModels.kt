package com.troshchii.reddit.ui.topnews.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize data class TopNewsDto(
    val data: ListingData
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
            val data: RedditPost
        ) : Parcelable {

            @Parcelize data class RedditPost(
                val id: String,
                val thumbnail: String,
                val preview: Preview?,
                val title: String,
                val author: String,
                val created_utc: String,
                val numComments: String
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
            }
        }
    }
}