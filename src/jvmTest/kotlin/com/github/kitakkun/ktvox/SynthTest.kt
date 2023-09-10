package com.github.kitakkun.ktvox

import kotlinx.coroutines.test.runTest
import org.junit.Test

class SynthTest : BaseKtVoxApiTest() {
    @Test
    fun testPostSynthesis() = runTest {
        val query = api.createAudioQuery(
            text = "こんにちは",
            speaker = 0,
        )
        api.postSynthesis(
            speaker = 0,
            audioQuery = query,
        )
        // To save the audio file run the following code.
        // val file = File("output.wav")
        // val bytes = api.postSynthesis(speaker = 0, audioQuery = query)
        // file.writeBytes(bytes)
    }

    @Test
    fun testPostCancellableSynthesis() = runTest {
        val query = api.createAudioQuery(
            text = "こんにちは",
            speaker = 0,
        )
        api.postCancellableSynthesis(
            speaker = 0,
            audioQuery = query,
        )
    }

    @Test
    fun testPostMultiSynthesis() = runTest {
        val messages = listOf(
            "こんにちは",
            "おはよう",
            "こんばんは",
        )
        val queries = messages.map { api.createAudioQuery(text = it, speaker = 0) }
        api.postMultiSynthesis(
            speaker = 0,
            audioQueries = queries,
        )
    }

    @Test
    fun testPostMorphableTargets() = runTest {
        api.postMorphableTargets(baseSpeakers = listOf(0, 1, 2))
    }

    @Test
    fun testPostSynthesisMorphing() = runTest {
        val query = api.createAudioQuery(
            text = "こんにちは",
            speaker = 0,
        )
        api.postSynthesisMorphing(
            baseSpeaker = 0,
            targetSpeaker = 1,
            morphRate = 0.5,
            audioQuery = query,
        )
    }
}
