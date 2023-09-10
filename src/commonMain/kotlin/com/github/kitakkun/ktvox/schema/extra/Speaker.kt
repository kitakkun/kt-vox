package com.github.kitakkun.ktvox.schema.extra

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Speaker(
    @SerialName("supported_features")
    val supportedFeatures: SupportedFeatures,
    val name: String,
    @SerialName("speaker_uuid")
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
    @SerialName("adjust_mora_pitch")
    val adjustMoraPitch: Boolean = false,
    @SerialName("adjust_phoneme_length")
    val adjustPhonemeLength: Boolean = false,
    @SerialName("adjust_speed_scale")
    val adjustSpeedScale: Boolean = false,
    @SerialName("adjust_pitch_scale")
    val adjustPitchScale: Boolean = false,
    @SerialName("adjust_intonation_scale")
    val adjustIntonationScale: Boolean = false,
    @SerialName("adjust_volume_scale")
    val adjustVolumeScale: Boolean = false,
    @SerialName("interrogative_upspeak")
    val interrogativeUpspeak: Boolean = false,
    @SerialName("synthesis_morphing")
    val synthesisMorphing: Boolean = false,
    @SerialName("manage_library")
    val manageLibrary: Boolean = false,
)
