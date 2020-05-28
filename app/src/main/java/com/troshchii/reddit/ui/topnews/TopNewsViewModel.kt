package com.troshchii.reddit.ui.topnews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.troshchii.reddit.core.BaseViewModel
import com.troshchii.reddit.core.exception.Failure
import com.troshchii.reddit.core.extensions.logI
import com.troshchii.reddit.core.functional.Either
import com.troshchii.reddit.ui.topnews.data.RedditPost
import kotlinx.coroutines.launch


private const val VISIBLE_THRESHOLD = 5


class TopNewsViewModel constructor(private val repository: TopNewsRepository) : BaseViewModel() {

    val topNews: LiveData<Either<Failure, List<RedditPost>>> = MutableLiveData()
    var isLoadingMore: LiveData<Boolean> = MutableLiveData(false)

    init {
        logI(tag, "init")
        viewModelScope.launch {
            topNews.postUpdate(repository.initialLoad())
        }
    }

    fun listScrolled(visibleItemCount: Int, lastVisibleItemPosition: Int, totalItemCount: Int) {
        //TODO: use lastVisibleItemPosition or not?
//        if (visibleItemCount + lastVisibleItemPosition + VISIBLE_THRESHOLD >= totalItemCount) {

        if (isLoadingMore.value?.not() == true && lastVisibleItemPosition + VISIBLE_THRESHOLD >= totalItemCount) {
            logI(tag, "load more")

            isLoadingMore.postUpdate(true)

            viewModelScope.launch {
                //TODO: right update not replace topNews
                val value = repository.loadMore()
                logI(tag, "load more. 1")
                topNews.postUpdate(value)
                logI(tag, "load more. 2")
                this@TopNewsViewModel.isLoadingMore.postUpdate(false)
            }
        }
    }
}