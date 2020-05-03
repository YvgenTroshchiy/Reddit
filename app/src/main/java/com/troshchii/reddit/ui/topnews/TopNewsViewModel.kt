package com.troshchii.reddit.ui.topnews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.troshchii.reddit.core.BaseViewModel
import com.troshchii.reddit.core.exception.Failure
import com.troshchii.reddit.core.extensions.logI
import com.troshchii.reddit.core.functional.Either
import com.troshchii.reddit.domain.TopNewsUseCase
import com.troshchii.reddit.ui.topnews.data.RedditPost
import kotlinx.coroutines.launch


class TopNewsViewModel constructor(private val topNewsUseCase: TopNewsUseCase) : BaseViewModel() {

    val topNews: LiveData<Either<Failure, List<RedditPost>>> = MutableLiveData()

    companion object {
        private const val VISIBLE_THRESHOLD = 5
    }

    init {
        logI(tag, "init")

        loadTopNews()
    }

    private fun loadTopNews() {
        viewModelScope.launch { topNews.postUpdate(topNewsUseCase.execute()) }
    }

    fun listScrolled(visibleItemCount: Int, lastVisibleItemPosition: Int, totalItemCount: Int) {
//        if (visibleItemCount + lastVisibleItemPosition + VISIBLE_THRESHOLD >= totalItemCount) {
        if (lastVisibleItemPosition + VISIBLE_THRESHOLD >= totalItemCount) {
            logI(tag, "load more")
        }
    }
}