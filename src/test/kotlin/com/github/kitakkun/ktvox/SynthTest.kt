package com.github.kitakkun.ktvox

import com.github.kitakkun.ktvox.api.query.QueryApi
import com.github.kitakkun.ktvox.api.synth.SynthApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.koin.test.inject
import kotlin.test.assertNotNull

class SynthTest : BaseKtVoxApiTest() {
    private val queryApi: QueryApi by inject()
    private val synthApi: SynthApi by inject()

    @Test
    fun testPostSynthesis() = runTest {
        val query = queryApi.createAudioQuery(
            text = "こんにちは",
            speaker = 0,
        ).body()
        assertNotNull(query)
        val response = synthApi.postSynthesis(
            speaker = 0,
            audioQuery = query,
        )
        assert(response.isSuccessful)
        // To save the audio file run the following code.
        // val file = File("output.wav")
        // val bytes = response.body()?.bytes() ?: throw Exception("body is null")
        // file.writeBytes(bytes)
    }

    @Test
    fun testPostMultiSynthesis() = runTest {
        val messages = listOf(
            "こんにちは",
            "おはよう",
            "こんばんは",
        )
        val queries = messages.mapNotNull { queryApi.createAudioQuery(text = it, speaker = 0).body() }
        val response = synthApi.postMultiSynthesis(
            speaker = 0,
            audioQueries = queries,
        )
        assert(response.isSuccessful)
    }

    @Test
    fun testPostMorphableTargets() = runTest {
        val response = synthApi.postMorphableTargets(
            baseSpeakers = listOf(0, 1, 2),
        )
        assert(response.isSuccessful)
    }

    @Test
    fun testPostSynthesisMorphing() = runTest {
        val query = queryApi.createAudioQuery(
            text = "こんにちは",
            speaker = 0,
        ).body()
        assertNotNull(query)
        val response = synthApi.postSynthesisMorphing(
            baseSpeaker = 0,
            targetSpeaker = 0,
            morphRate = 0.5,
            audioQuery = query,
        )
        assert(response.isSuccessful)
    }
}
