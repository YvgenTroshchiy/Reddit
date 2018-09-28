package com.troshchii.reddit.ui.topnews

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.troshchii.reddit.exception.Failure
import com.troshchii.reddit.extensions.plusAssign
import com.troshchii.reddit.functional.Either
import com.troshchii.reddit.network.TopNews
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class TopNewsViewModel @Inject constructor(
    topNewsUseCase: TopNewsUseCase
    //TODO: Add mapper
) : ViewModel() {

    private val disposable = CompositeDisposable()

    // TODO: Use List<News>
    val topNews: MutableLiveData<Either<Failure, TopNews>> = MutableLiveData()

    init {
        loadTopNews(topNewsUseCase)
    }

    private fun loadTopNews(topNewsUseCase: TopNewsUseCase) {
        disposable += topNewsUseCase.execute()
            .subscribe({
                topNews.value = Either.Right(it)
            }, {
                topNews.value = Either.Left(Failure.ServerError(message = it.message.toString()))
            })
    }

    override fun onCleared() = disposable.clear()

}