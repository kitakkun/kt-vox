package com.github.kitakkun.ktvox.schema.extra

import kotlinx.serialization.Serializable

@Serializable
data class SpeakerInfo(
    val policy: String,
    val portrait: String,
    val styleInfos: List<StyleInfo>,
)

@Serializable
data class StyleInfo(
    val id: Int,
    val icon: String,
    val portrait: String,
    val voiceSamples: List<String>,
)