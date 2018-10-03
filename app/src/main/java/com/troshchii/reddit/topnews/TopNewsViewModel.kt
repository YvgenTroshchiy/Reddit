package com.troshchii.reddit.topnews

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.troshchii.reddit.core.exception.Failure
import com.troshchii.reddit.core.extensions.getLogTag
import com.troshchii.reddit.core.extensions.logI
import com.troshchii.reddit.core.extensions.plusAssign
import com.troshchii.reddit.core.functional.Either
import com.troshchii.reddit.topnews.model.News
import com.troshchii.reddit.topnews.model.toTopNews
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class TopNewsViewModel @Inject constructor(topNewsUseCase: TopNewsUseCase) : ViewModel() {

    private val tag = getLogTag<TopNewsViewModel>()

    private val disposable = CompositeDisposable()

    val topNews: MutableLiveData<Either<Failure, List<News>>> = MutableLiveData()

    init {
        logI(tag, "init")
        loadTopNews(topNewsUseCase)
    }

    private fun loadTopNews(topNewsUseCase: TopNewsUseCase) {
        disposable += topNewsUseCase.execute()
            .subscribe({
                topNews.value = Either.Right(it.toTopNews())
            }, {
                topNews.value = Either.Left(Failure.ServerError(message = it.message.toString()))
            })
    }

    override fun onCleared() = disposable.clear()

}