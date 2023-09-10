package com.github.kitakkun.ktvox.converter

import com.github.kitakkun.ktvox.schema.dictionary.UserDictWord
import com.github.kitakkun.ktvox.schema.dictionary.UserDictWordMap
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class UserDictMapConverterTest {
    @Test
    fun testConvertSample() = runTest {
        val text = """
            {
              "property1": {
                "surface": "string",
                "priority": 10,
                "context_id": 1348,
                "part_of_speech": "string",
                "part_of_speech_detail_1": "string",
                "part_of_speech_detail_2": "string",
                "part_of_speech_detail_3": "string",
                "inflectional_type": "string",
                "inflectional_form": "string",
                "stem": "string",
                "yomi": "string",
                "pronunciation": "string",
                "accent_type": 0,
                "mora_count": 0,
                "accent_associative_rule": "string"
              },
              "property2": {
                "surface": "string",
                "priority": 10,
                "context_id": 1348,
                "part_of_speech": "string",
                "part_of_speech_detail_1": "string",
                "part_of_speech_detail_2": "string",
                "part_of_speech_detail_3": "string",
                "inflectional_type": "string",
                "inflectional_form": "string",
                "stem": "string",
                "yomi": "string",
                "pronunciation": "string",
                "accent_type": 0,
                "mora_count": 0,
                "accent_associative_rule": "string"
              }
            }
        """.trimIndent()
        val converter = UserDictMapConverter()
        val actual = converter.convert(text)
        assertEquals(
            expected = UserDictWordMap(
                mapOf(
                    "property1" to UserDictWord(
                        surface = "string",
                        priority = 10,
                        contextId = 1348,
                        partOfSpeech = "string",
                        partOfSpeechDetail1 = "string",
                        partOfSpeechDetail2 = "string",
                        partOfSpeechDetail3 = "string",
                        inflectionalType = "string",
                        inflectionalForm = "string",
                        stem = "string",
                        yomi = "string",
                        pronunciation = "string",
                        accentType = 0,
                        moraCount = 0,
                        accentAssociativeRule = "string",
                    ),
                    "property2" to UserDictWord(
                        surface = "string",
                        priority = 10,
                        contextId = 1348,
                        partOfSpeech = "string",
                        partOfSpeechDetail1 = "string",
                        partOfSpeechDetail2 = "string",
                        partOfSpeechDetail3 = "string",
                        inflectionalType = "string",
                        inflectionalForm = "string",
                        stem = "string",
                        yomi = "string",
                        pronunciation = "string",
                        accentType = 0,
                        moraCount = 0,
                        accentAssociativeRule = "string",
                    )
                )
            ),
            actual = actual,
        )
    }
}
