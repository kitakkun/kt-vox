package com.github.kitakkun.ktvox

import com.github.kitakkun.ktvox.schema.extra.Preset
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ExtraApiTest : BaseKtVoxApiTest() {
    @Test
    fun testConnectWaves() = runTest {
        val query = api.createAudioQuery(
            text = "こんにちは",
            speaker = 0,
        )
        val wave = api.postSynthesis(
            speaker = 0,
            audioQuery = query,
        )
        api.connectWaves(
            waves = listOf(wave, wave),
        )
    }

    @Test
    fun testGetVersion() = runTest {
        api.getVersion()
    }

    @Test
    fun testGetCoreVersions() = runTest {
        api.getCoreVersions()
    }

    @Test
    fun testGetDownloadableLibraries() = runTest {
        api.getDownloadableLibraries()
    }

    @Test
    fun testGetSpeakers() = runTest {
        api.getSpeakers()
    }

    @Test
    fun testGetSpeakerInfo() = runTest {
        api.getSpeakerInfo(
            speakerUuid = "7ffcb7ce-00ec-4bdc-82cd-45a8889e43ff",
            coreVersion = null
        )
    }

    @Test
    fun testGetPresets() = runTest {
        api.getPresets()
    }

    @Test
    fun testAddPreset() = runTest {
        val preset = Preset(
            id = 1,
            name = "サンプルプリセット",
            speakerUuid = "7ffcb7ce-00ec-4bdc-82cd-45a8889e43ff",
            styleId = 0,
            speedScale = 1.0,
            pitchScale = 0.0,
            intonationScale = 1.0,
            volumeScale = 1.0,
            prePhonemeLength = 0.1,
            postPhonemeLength = 0.1,
        )
        api.addPreset(preset)
    }

    @Test
    fun testUpdatePreset() = runTest {
        val preset = Preset(
            id = 1,
            name = "サンプルプリセット",
            speakerUuid = "7ffcb7ce-00ec-4bdc-82cd-45a8889e43ff",
            styleId = 0,
            speedScale = 1.0,
            pitchScale = 0.0,
            intonationScale = 1.0,
            volumeScale = 1.0,
            prePhonemeLength = 0.1,
            postPhonemeLength = 0.1,
        )
        val presetId = api.addPreset(preset)
        api.updatePreset(preset.copy(id = presetId, name = "updated"))
    }

    @Test
    fun testDeletePreset() = runTest {
        val preset = Preset(
            id = 1,
            name = "サンプルプリセット",
            speakerUuid = "7ffcb7ce-00ec-4bdc-82cd-45a8889e43ff",
            styleId = 0,
            speedScale = 1.0,
            pitchScale = 0.0,
            intonationScale = 1.0,
            volumeScale = 1.0,
            prePhonemeLength = 0.1,
            postPhonemeLength = 0.1,
        )
        val presetId = api.addPreset(preset)
        api.deletePreset(presetId)
    }

    @Test
    fun testGetIsInitializedSpeaker() = runTest {
        api.initializeSpeaker(speaker = 0)
        api.getIsInitializedSpeaker(0)
    }

    @Test
    fun testInitializeSpeaker() = runTest {
        api.initializeSpeaker(0)
    }

    @Test
    fun testGetSupportedDevices() = runTest {
        api.getSupportedDevices()
    }

    @Test
    fun testGetEngineManifest() = runTest {
        api.getEngineManifest()
    }
}
