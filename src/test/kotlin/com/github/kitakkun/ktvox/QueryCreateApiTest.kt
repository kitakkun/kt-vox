package com.github.kitakkun.ktvox

import com.github.kitakkun.ktvox.api.query.QueryApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.koin.test.inject
import kotlin.test.assertTrue

class QueryCreateApiTest : BaseKtVoxApiTest() {

    private val api: QueryApi by inject()

    @Test
    fun testCreateAudioQuery() = runTest {
        val response = api.createAudioQuery(
            text = "こんにちは",
            speaker = 1,
        )
        assertTrue(response.isSuccessful)
    }

    @Test
    fun testCreateAudioQueryFromPreset() = runTest {
        val response = api.createAudioQueryFromPreset(
            text = "こんにちは",
            presetId = 1,
        )
        assertTrue(response.isSuccessful)
    }
}
