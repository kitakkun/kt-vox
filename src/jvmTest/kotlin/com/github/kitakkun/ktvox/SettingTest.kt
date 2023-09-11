package com.github.kitakkun.ktvox

import kotlinx.coroutines.test.runTest
import org.junit.Test

class SettingTest : BaseKtVoxApiTest() {
    @Test
    fun testGetSetting() = runTest {
        api.getSetting()
    }

    @Test
    fun testPostSetting() = runTest {
        api.postSetting(
            corsPolicyMode = "localapps",
            allowOrigin = listOf("http://localhost:3000", "http://localhost:8080"),
        )
    }
}
