package com.github.kitakkun.ktvox

import kotlinx.coroutines.test.runTest
import org.junit.Test

class QueryCreateApiTest : BaseKtVoxApiTest() {
    @Test
    fun testCreateAudioQuery() = runTest {
        api.createAudioQuery(
            text = "こんにちは",
            speaker = 0,
        )
    }

    @Test
    fun testCreateAudioQueryFromPreset() = runTest {
        api.createAudioQueryFromPreset(
            text = "こんにちは",
            presetId = 1,
        )
    }
}
