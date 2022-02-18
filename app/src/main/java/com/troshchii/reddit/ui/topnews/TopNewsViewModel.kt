package com.troshchii.reddit.ui.topnews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.troshchii.reddit.core.Async
import com.troshchii.reddit.core.BaseViewModel
import com.troshchii.reddit.core.Loading
import com.troshchii.reddit.core.StateClass
import com.troshchii.reddit.core.extensions.logI
import com.troshchii.reddit.core.toAsync
import com.troshchii.reddit.core.utils.debugDelayAsync
import com.troshchii.reddit.ui.topnews.data.RedditPost
import javax.inject.Inject
import kotlinx.coroutines.launch

private const val VISIBLE_THRESHOLD = 5

class TopNewsState() {
    val topNews: List<RedditPost>? = null
    var isLoadingMore = false
}

class TopNewsViewModel @Inject constructor(private val repository: TopNewsRepository) : BaseViewModel() {

    val topNews: StateClass<Async<List<RedditPost>>> = StateClass(Loading())

    var isLoading: LiveData<Boolean> = MutableLiveData(false)
    var isLoadingMore: LiveData<Boolean> = MutableLiveData(false)

    init {
        logI(tag, "init")
        viewModelScope.launch {
            isLoading.postUpdate(true)
            debugDelayAsync(2) // To catch better loading more animation :)
            topNews.update(repository.initialLoad().toAsync())
            isLoading.postUpdate(false)
        }
    }

    fun listScrolled(visibleItemCount: Int, lastVisibleItemPosition: Int, totalItemCount: Int) {
        //TODO: use lastVisibleItemPosition or not?
//        if (visibleItemCount + lastVisibleItemPosition + VISIBLE_THRESHOLD >= totalItemCount) {

        if (isLoadingMore.value?.not() == true && lastVisibleItemPosition + VISIBLE_THRESHOLD >= totalItemCount) {
            logI(tag, "load more")

            viewModelScope.launch {
                isLoadingMore.postUpdate(true)
                debugDelayAsync(3) // To catch better loading more animation :)
                val value = repository.loadMore()
                topNews.update(value.toAsync())
                this@TopNewsViewModel.isLoadingMore.postUpdate(false)
            }
        }
    }
}
