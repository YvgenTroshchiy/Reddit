package com.troshchii.reddit.di

import com.troshchii.reddit.domain.TopNewsUseCase
import com.troshchii.reddit.ui.topnews.TopNewsRepository
import com.troshchii.reddit.ui.topnews.TopNewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { TopNewsViewModel(get()) }
}

val repositoryModule = module {
    factory { TopNewsRepository(get()) }
}

val useCaseModule = module {
    factory { TopNewsUseCase(get()) }
}

val allAppModules = listOf(viewModelModule, repositoryModule, networkModule, useCaseModule)