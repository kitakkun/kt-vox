package com.github.kitakkun.ktvox

import com.github.kitakkun.ktvox.api.extra.ExtraApi
import com.github.kitakkun.ktvox.api.query.QueryApi
import com.github.kitakkun.ktvox.api.synth.SynthApi
import com.github.kitakkun.ktvox.schema.extra.Preset
import com.google.gson.Gson
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.koin.test.inject
import kotlin.test.Ignore
import kotlin.test.assertTrue

class ExtraApiTest : BaseKtVoxApiTest() {
    private val extraApi: ExtraApi by inject()
    private val queryApi: QueryApi by inject()
    private val synthApi: SynthApi by inject()

    @Ignore("Not works yet")
    @Test
    fun testConnectWaves() = runTest {
        val query = queryApi.createAudioQuery(
            text = "こんにちは",
            speaker = 0,
        ).body() ?: throw Exception("body is null")
        val wave = synthApi.postSynthesis(
            speaker = 0,
            audioQuery = query,
        ).body()?.string() ?: throw Exception("body is null")
        val response = extraApi.connectWaves(
            waves = listOf(wave, wave),
        )
        assert(response.isSuccessful)
    }

    @Test
    fun testGetVersion() = runTest {
        val response = extraApi.getVersion()
        assert(response.isSuccessful)
    }

    @Test
    fun testGetCoreVersions() = runTest {
        val response = extraApi.getCoreVersions()
        assert(response.isSuccessful)
    }

    @Ignore("This test may not work yet")
    @Test
    fun testGetDownloadableLibraries() = runTest {
        val response = extraApi.getDownloadableLibraries()
        val libraries = response.body() ?: throw Exception("body is null")
        println(libraries.joinToString("\n"))
        assert(libraries.isNotEmpty())
    }

    @Test
    fun testGetSpeakers() = runTest {
        val response = extraApi.getSpeakers()
        val speakers = response.body() ?: throw Exception("body is null")
        println(speakers)
        assertTrue {
            speakers.isNotEmpty()
        }
    }

    @Test
    fun testGetSpeakerInfo() = runTest {
        val response = extraApi.getSpeakerInfo(
            speakerUuid = "7ffcb7ce-00ec-4bdc-82cd-45a8889e43ff",
            coreVersion = null
        )
        val speaker = response.body() ?: throw Exception("body is null")
        println(speaker)
    }

    @Test
    fun testGetPresets() = runTest {
        val response = extraApi.getPresets()
        val presets = response.body() ?: throw Exception("body is null")
        println(presets.joinToString("\n"))
        assertTrue {
            presets.isNotEmpty()
        }
    }

    @Test
    fun testAddPreset() = runTest {
        val presetString = """
            {
                "id": 0,
                "name": "string",
                "speaker_uuid": "string",
                "style_id": 0,
                "speedScale": 0,
                "pitchScale": 0,
                "intonationScale": 0,
                "volumeScale": 0,
                "prePhonemeLength": 0,
                "postPhonemeLength": 0
            }
            """
        val preset = Gson().fromJson(presetString, Preset::class.java)
        val response = extraApi.addPreset(preset)
        assert(response.isSuccessful)
        extraApi.deletePreset(response.body()!!)
    }

    @Test
    fun testUpdatePreset() = runTest {
        val presetString = """
            {
                "id": 0,
                "name": "string",
                "speaker_uuid": "string",
                "style_id": 0,
                "speedScale": 0,
                "pitchScale": 0,
                "intonationScale": 0,
                "volumeScale": 0,
                "prePhonemeLength": 0,
                "postPhonemeLength": 0
            }
            """
        val preset = Gson().fromJson(presetString, Preset::class.java)
        val response = extraApi.addPreset(preset)
        val presetId = response.body() ?: throw Exception("body is null")
        val response2 = extraApi.updatePreset(preset.copy(id = presetId, name = "updated"))
        assert(response2.isSuccessful)
        extraApi.deletePreset(presetId)
    }

    @Test
    fun testDeletePreset() = runTest {
        val presetString = """
            {
                "id": 0,
                "name": "string",
                "speaker_uuid": "string",
                "style_id": 0,
                "speedScale": 0,
                "pitchScale": 0,
                "intonationScale": 0,
                "volumeScale": 0,
                "prePhonemeLength": 0,
                "postPhonemeLength": 0
            }
            """
        val preset = Gson().fromJson(presetString, Preset::class.java)
        val response = extraApi.addPreset(preset)
        val presetId = response.body() ?: throw Exception("body is null")
        val response2 = extraApi.deletePreset(presetId)
        assert(response2.isSuccessful)
    }

    @Test
    fun testGetIsInitializedSpeaker() = runTest {
        extraApi.initializeSpeaker(speaker = 0)
        val response = extraApi.getIsInitializedSpeaker(0)
        val isInitialized = response.body()
        assert(isInitialized == true)
    }

    @Test
    fun testInitializeSpeaker() = runTest {
        val response = extraApi.initializeSpeaker(0)
        assert(response.isSuccessful)
    }

    @Test
    fun testGetSupportedDevices() = runTest {
        val response = extraApi.getSupportedDevices()
        assert(response.isSuccessful)
    }

    @Test
    fun testGetEngineManifest() = runTest {
        val response = extraApi.getEngineManifest()
        assert(response.isSuccessful)
    }
}
