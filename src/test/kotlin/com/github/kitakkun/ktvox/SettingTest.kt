package com.github.kitakkun.ktvox

import com.github.kitakkun.ktvox.annotation.ExperimentalKtVoxApi
import com.github.kitakkun.ktvox.api.setting.SettingApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.koin.core.component.inject

class SettingTest : BaseKtVoxApiTest() {
    private val api: SettingApi by inject()

    @OptIn(ExperimentalKtVoxApi::class)
    @Test
    fun testGetSetting() = runTest {
        val response = api.getSetting()
        println(response.body())
        assert(response.isSuccessful)
    }

    @OptIn(ExperimentalKtVoxApi::class)
    @Test
    fun testPostSetting() = runTest {
        val response = api.postSetting(
            corsPolicyMode = "localapps",
            allowOrigin = listOf("http://localhost:3000", "http://localhost:8080"),
        )
        assert(response.isSuccessful)
    }
}
