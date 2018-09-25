package com.troshchii.reddit.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface RedditService {

    @GET("top") fun topNews(
        @Query("limit") limit: Int,
        @Query("after") after: String? = null
    ): Single<TopNews>

}