package com.github.kitakkun.ktvox.schema.query

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class AudioQuery(
    @SerializedName("accent_phrases")
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
    @SerializedName("consonant_length")
    val consonantLength: Double?,
    val vowel: String,
    @SerializedName("vowel_length")
    val vowelLength: Double,
    val pitch: Double,
)

@Serializable
data class AccentPhrase(
    val moras: List<Mora>,
    val accent: Int,
    @SerializedName("pause_mora")
    val pauseMora: Mora?,
    @SerializedName("is_interrogative")
    val isInterrogative: Boolean?,
)
