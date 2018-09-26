package com.troshchii.reddit.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class TopNewsViewModel @Inject constructor() : ViewModel() {

    private val disposable = CompositeDisposable()

    val topNews: LiveData<List<News>>? = MutableLiveData()

    override fun onCleared() = disposable.clear()

}