package com.troshchii.reddit.ui.topnews

import com.nhaarman.mockito_kotlin.verify
import com.troshchii.reddit.di.allAppModules
import com.troshchii.reddit.di.useCaseModule
import com.troshchii.reddit.domain.TopNewsUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject


class TopNewsUseCaseTest : AutoCloseKoinTest() {

    //    lateinit var useCase: TopNewsUseCase
    val useCase by inject<TopNewsUseCase>()
//    val service by inject<RedditService>()


    @Before
    fun before() {
        startKoin {
            modules(useCaseModule)
        }
    }

//    @Before fun setUp() {
//        val module = module {
//            single { TopNewsUseCase(get()) }
//        }
//        startKoin {
//            listOf(module)
//        }
//    }

    @Test fun `should get data from UseCase`() {
//        verify(useCase).

//        runBlocking {
//            useCase.execute()

//            verify(service).topNews(1)
//        }
    }
}