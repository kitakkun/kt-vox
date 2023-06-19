package com.github.kitakkun.ktvox

import com.github.kitakkun.ktvox.api.query.QueryEditApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.koin.test.inject
import kotlin.test.assertTrue

class QueryEditApiTest : BaseKtVoxApiTest() {
    private val api: QueryEditApi by inject()

    @Test
    fun testGetAccentPhrases() = runTest {
        val response = api.getAccentPhrases(
            text = "こんにちは",
            speaker = 1,
        )
        assertTrue(response.isSuccessful)
    }

    @Test
    fun testGetMoraData() = runTest {
        val phrases = api.getAccentPhrases(
            text = "こんにちは",
            speaker = 1,
        ).body() ?: throw Exception("body is null")
        println(phrases)
        val response = api.getMoraData(
            speaker = 0,
            accentPhrases = phrases,
        )
        assert(response.isSuccessful)
    }

    @Test
    fun testGetMoraLength() = runTest {
        val phrases = api.getAccentPhrases(
            text = "こんにちは",
            speaker = 1,
        ).body() ?: throw Exception("body is null")
        val response = api.getMoraLength(
            speaker = 1,
            accentPhrases = phrases,
        )
        assert(response.isSuccessful)
    }

    @Test
    fun testGetMoraPitch() = runTest {
        val phrases = api.getAccentPhrases(
            text = "こんにちは",
            speaker = 1,
        ).body() ?: throw Exception("body is null")
        val response = api.getMoraPitch(
            speaker = 1,
            accentPhrases = phrases,
        )
        assert(response.isSuccessful)
    }
}