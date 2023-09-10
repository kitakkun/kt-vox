package com.github.kitakkun.ktvox.schema.extra

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpeakerWithSpeakerInfo(
    val speaker: Speaker,
    @SerialName("speaker_info")
    val speakerInfo: SpeakerInfo,
)

@Serializable
data class DownloadableLibrary(
    val name: String,
    val uuid: String,
    val version: String,
    @SerialName("download_url")
    val downloadUrl: String,
    val bytes: Int,
    val speakers: List<SpeakerWithSpeakerInfo>,
)
