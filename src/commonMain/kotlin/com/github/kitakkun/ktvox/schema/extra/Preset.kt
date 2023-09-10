package com.github.kitakkun.ktvox.schema.extra

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Preset(
    val id: Int,
    val name: String,
    @SerialName("speaker_uuid")
    val speakerUuid: String,
    @SerialName("style_id")
    val styleId: Int,
    val speedScale: Double,
    val pitchScale: Double,
    val intonationScale: Double,
    val volumeScale: Double,
    val prePhonemeLength: Double,
    val postPhonemeLength: Double,
)
