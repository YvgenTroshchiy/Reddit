package com.troshchii.reddit.di.koin

import com.troshchii.reddit.di.networkModule
import com.troshchii.reddit.ui.topnews.TopNewsUseCase
import com.troshchii.reddit.ui.topnews.TopNewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { TopNewsViewModel(get()) }
}

val useCaseModule = module {
    factory { TopNewsUseCase(get()) }
}

val allAppModules = listOf(viewModelModule, networkModule, useCaseModule)