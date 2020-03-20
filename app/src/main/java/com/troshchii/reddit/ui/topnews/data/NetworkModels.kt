package com.troshchii.reddit.ui.topnews.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize data class TopNewsDto(
    val data: Data
) : Parcelable {

    // ListingData
    @Parcelize data class Data(
        val modhash: String,
        val dist: Int,
        val children: List<Children>,
        val after: String?,
        val before: String?
    ) : Parcelable {

        @Parcelize data class Children(
            val kind: String,
            val data: ChildrenData
        ) : Parcelable {

            // RedditPost
            @Parcelize data class ChildrenData(
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
                }
            }
        }
    }
}