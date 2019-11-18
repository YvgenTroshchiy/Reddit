package com.troshchii.reddit.ui.topnews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.troshchii.reddit.core.BaseViewModel
import com.troshchii.reddit.core.exception.Failure
import com.troshchii.reddit.core.extensions.logI
import com.troshchii.reddit.core.functional.Either
import com.troshchii.reddit.ui.topnews.data.RedditPost
import com.troshchii.reddit.ui.topnews.pading.RedditDataSourceFactory


typealias TopNewsUiData = LiveData<Either<Failure, PagedList<RedditPost>>>


class TopNewsViewModel(
    redditDataSourceFactory: RedditDataSourceFactory
) : BaseViewModel() {

    var topNews: TopNewsUiData = MutableLiveData()

    init {
        logI(tag, "init")

        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setInitialLoadSizeHint(30) // default: page size * 3
            .setPrefetchDistance(10) // default: page size
            .setEnablePlaceholders(true) // default: true
            .build()

        topNews = LivePagedListBuilder(redditDataSourceFactory, config).build()
    }
}