package com.troshchii.reddit.ui.topnews

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.troshchii.reddit.exception.Failure
import com.troshchii.reddit.extensions.plusAssign
import com.troshchii.reddit.functional.Either
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class TopNewsViewModel @Inject constructor(
    topNewsUseCase: TopNewsUseCase,
    private val topNewsMapper: TopNewsMapper
) : ViewModel() {

    private val disposable = CompositeDisposable()

    val topNews: MutableLiveData<Either<Failure, List<News>>> = MutableLiveData()

    init {
        loadTopNews(topNewsUseCase)
    }

    private fun loadTopNews(topNewsUseCase: TopNewsUseCase) {
        disposable += topNewsUseCase.execute()
            .subscribe({
                topNews.value = Either.Right(topNewsMapper.computeTopNews(it))
            }, {
                topNews.value = Either.Left(Failure.ServerError(message = it.message.toString()))
            })
    }

    override fun onCleared() = disposable.clear()

}