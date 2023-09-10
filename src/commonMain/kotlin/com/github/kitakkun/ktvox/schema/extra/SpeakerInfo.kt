package com.github.kitakkun.ktvox.schema.extra

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpeakerInfo(
    val policy: String,
    val portrait: String,
    @SerialName("style_infos")
    val styleInfos: List<StyleInfo>,
)

@Serializable
data class StyleInfo(
    val id: Int,
    val icon: String,
    val portrait: String?,
    @SerialName("voice_samples")
    val voiceSamples: List<String>,
)
