package com.github.kitakkun.ktvox.schema.query

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AudioQuery(
    @SerialName("accent_phrases")
    val accentPhrases: List<AccentPhrase>,
    val speedScale: Double,
    val pitchScale: Double,
    val intonationScale: Double,
    val volumeScale: Double,
    val prePhonemeLength: Double,
    val postPhonemeLength: Double,
    val outputSamplingRate: Int,
    val outputStereo: Boolean,
    val kana: String?,
)

@Serializable
data class Mora(
    val text: String,
    val consonant: String?,
    @SerialName("consonant_length")
    val consonantLength: Double?,
    val vowel: String,
    @SerialName("vowel_length")
    val vowelLength: Double,
    val pitch: Double,
)

@Serializable
data class AccentPhrase(
    val moras: List<Mora>,
    val accent: Int,
    @SerialName("pause_mora")
    val pauseMora: Mora?,
    @SerialName("is_interrogative")
    val isInterrogative: Boolean?,
)
