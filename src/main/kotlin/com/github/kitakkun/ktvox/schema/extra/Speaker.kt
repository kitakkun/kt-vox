package com.github.kitakkun.ktvox.schema.extra

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Speaker(
    @SerializedName("supported_features")
    val supportedFeatures: SupportedFeatures,
    val name: String,
    @SerializedName("speaker_uuid")
    val speakerUuid: String,
    val styles: List<Style>,
    val version: String,
)

@Serializable
data class Style(
    val name: String,
    val id: Int,
)

@Serializable
data class SupportedFeatures(
    @SerializedName("permitted_synthesis_morphing")
    val permittedSynthesisMorphing: String,
)