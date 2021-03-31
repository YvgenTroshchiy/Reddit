package com.troshchii.reddit

import androidx.test.platform.app.InstrumentationRegistry
import com.troshchii.reddit.di.allAppModules
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

@RunWith(AndroidJUnit4ClassRunner::class)
class CheckModulesTest : KoinTest {

    // TODO: Fix test
    @Ignore
    @Test fun checkAllModules() {
        koinApplication {
            modules(allAppModules)
            androidContext(InstrumentationRegistry.getInstrumentation().context)
        }.checkModules()
    }
}