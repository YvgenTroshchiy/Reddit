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


typealias TopNewsUiData = Either<Failure, PagedList<RedditPost>>

const val PAGE_SIZE = 20

class TopNewsViewModel(
    redditDataSourceFactory: RedditDataSourceFactory
) : BaseViewModel() {

    var topNews: LiveData<TopNewsUiData> = MutableLiveData()

    init {
        logI(tag, "init")

        val config = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setInitialLoadSizeHint(30) // default: page size * 3
            .setPrefetchDistance(10) // default: page size
            .setEnablePlaceholders(true) // default: true
            .build()

        topNews = LivePagedListBuilder(redditDataSourceFactory, config).build()
    }
}