package com.troshchii.reddit.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.troshchii.reddit.core.vm.ViewModelFactory
import com.troshchii.reddit.core.vm.ViewModelKey
import com.troshchii.reddit.ui.topnews.TopNewsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module abstract class ViewModelModule {

    @Binds internal abstract fun viewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(TopNewsViewModel::class)
    abstract fun topNewsViewModel(topNewsViewModel: TopNewsViewModel): ViewModel

}