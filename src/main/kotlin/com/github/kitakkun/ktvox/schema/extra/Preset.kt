package com.github.kitakkun.ktvox.schema.extra

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Preset(
    val id: Int,
    val name: String,
    @SerializedName("speaker_uuid")
    val speakerUuid: String,
    @SerializedName("style_id")
    val styleId: Int,
    val speedScale: Double,
    val pitchScale: Double,
    val intonationScale: Double,
    val volumeScale: Double,
    val prePhonemeLength: Double,
    val postPhonemeLength: Double,
)
