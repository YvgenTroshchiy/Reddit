package com.troshchii.reddit.ui.topnews.pading

import androidx.paging.DataSource
import com.troshchii.reddit.ui.topnews.TopNewsUiData


class RedditDataSourceFactory : DataSource.Factory<Long, TopNewsUiData>() {

    override fun create(): DataSource<Long, TopNewsUiData> {
        return RedditDataSource()
    }
}