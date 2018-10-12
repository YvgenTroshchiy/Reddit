package com.troshchii.reddit.ui.topnews.pading

import androidx.paging.PageKeyedDataSource
import com.troshchii.reddit.ui.topnews.model.RedditPost


class RedditDataSource : PageKeyedDataSource<String, RedditPost>() {

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, RedditPost>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, RedditPost>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, RedditPost>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}