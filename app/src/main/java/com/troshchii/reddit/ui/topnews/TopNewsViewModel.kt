package com.troshchii.reddit.ui.topnews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.troshchii.reddit.core.exception.Failure
import com.troshchii.reddit.core.extensions.logI
import com.troshchii.reddit.core.functional.Either
import com.troshchii.reddit.domain.TopNewsUseCase
import com.troshchii.reddit.network.RedditService
import com.troshchii.reddit.ui.BaseViewModel
import com.troshchii.reddit.ui.topnews.data.RedditPost
import com.troshchii.reddit.ui.topnews.data.toTopNews
import kotlinx.coroutines.launch


class TopNewsViewModel constructor(private val topNewsUseCase: TopNewsUseCase, private val service: RedditService) : BaseViewModel() {

    val topNews: LiveData<Either<Failure, List<RedditPost>>> = MutableLiveData()

    init {
        logI(tag, "init")

        loadTopNews()
    }

    private fun loadTopNews() {

        launch {
            val result = service.topNews(100)

            if (result.isSuccessful && result.body() != null) {
                topNews.postUpdate(Either.Right(result.body()!!.toTopNews()))
            } else {
                topNews.postUpdate(Either.Left(Failure.ServerError(message = result.message())))
            }
        }
    }
}