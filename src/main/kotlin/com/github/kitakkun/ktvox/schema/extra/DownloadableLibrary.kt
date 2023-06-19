package com.github.kitakkun.ktvox.schema.extra

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class DownloadableLibrary(
    @SerializedName("download_url")
    val downloadUrl: String,
    val bytes: Int,
    val speaker: Speaker,
    @SerializedName("speaker_info")
    val speakerInfo: SpeakerInfo,
)
