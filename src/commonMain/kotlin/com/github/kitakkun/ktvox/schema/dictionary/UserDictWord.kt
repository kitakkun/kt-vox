package com.github.kitakkun.ktvox.schema.dictionary

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class UserDictWordMap(
    val properties: Map<String, UserDictWord>
)

@Serializable
data class UserDictWord(
    val surface: String,
    val priority: Int,
    @SerialName("context_id")
    val contextId: Int?,
    @SerialName("part_of_speech")
    val partOfSpeech: String,
    @SerialName("part_of_speech_detail_1")
    val partOfSpeechDetail1: String,
    @SerialName("part_of_speech_detail_2")
    val partOfSpeechDetail2: String,
    @SerialName("part_of_speech_detail_3")
    val partOfSpeechDetail3: String,
    @SerialName("inflectional_type")
    val inflectionalType: String,
    @SerialName("inflectional_form")
    val inflectionalForm: String,
    val stem: String,
    val yomi: String,
    val pronunciation: String,
    @SerialName("accent_type")
    val accentType: Int,
    @SerialName("mora_count")
    val moraCount: Int?,
    @SerialName("accent_associative_rule")
    val accentAssociativeRule: String?,
)

