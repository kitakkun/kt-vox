package com.github.kitakkun.ktvox.schema.dictionary

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class UserDictWord(
    val surface: String,
    val priority: Int,
    @SerializedName("context_id")
    val contextId: Int?,
    @SerializedName("part_of_speech")
    val partOfSpeech: String,
    @SerializedName("part_of_speech_detail_1")
    val partOfSpeechDetail1: String,
    @SerializedName("part_of_speech_detail_2")
    val partOfSpeechDetail2: String,
    @SerializedName("part_of_speech_detail_3")
    val partOfSpeechDetail3: String,
    @SerializedName("inflectional_type")
    val inflectionalType: String,
    @SerializedName("inflectional_form")
    val inflectionalForm: String,
    val stem: String,
    val yomi: String,
    val pronunciation: String,
    @SerializedName("accent_type")
    val accentType: Int,
    @SerializedName("mora_count")
    val moraCount: Int?,
    @SerializedName("accent_associative_rule")
    val accentAssociativeRule: String?,
)

