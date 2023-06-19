package com.github.kitakkun.ktvox

import com.github.kitakkun.ktvox.module.ktVoxModule
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

abstract class BaseKtVoxApiTest : KoinTest {
    @BeforeTest
    fun setup() {
        startKoin {
            modules(ktVoxModule)
        }
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }
}