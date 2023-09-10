package com.github.kitakkun.ktvox

import kotlinx.coroutines.test.runTest
import org.junit.Test

class QueryEditApiTest : BaseKtVoxApiTest() {
    @Test
    fun testGetAccentPhrases() = runTest {
        api.getAccentPhrases(
            text = "こんにちは",
            speaker = 1,
        )
    }

    @Test
    fun testGetMoraData() = runTest {
        val phrases = api.getAccentPhrases(
            text = "こんにちは",
            speaker = 1,
        )
        println(phrases)
        api.getMoraData(
            speaker = 0,
            accentPhrases = phrases,
        )
    }

    @Test
    fun testGetMoraLength() = runTest {
        val phrases = api.getAccentPhrases(
            text = "こんにちは",
            speaker = 1,
        )
        api.getMoraLength(
            speaker = 1,
            accentPhrases = phrases,
        )
    }

    @Test
    fun testGetMoraPitch() = runTest {
        val phrases = api.getAccentPhrases(
            text = "こんにちは",
            speaker = 1,
        )
        api.getMoraPitch(
            speaker = 1,
            accentPhrases = phrases,
        )
    }
}
