package com.troshchii.reddit

import com.troshchii.reddit.di.allAppModules
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.koin.test.mock.MockProviderRule
import org.mockito.Mockito

class CheckModulesTest : KoinTest {

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    // TODO: Investigate why it's fails
    @Ignore
    @Test
    fun checkAllModules() = checkModules {
        modules(allAppModules)
        // other KoinApplication attributes if needed
    }
}