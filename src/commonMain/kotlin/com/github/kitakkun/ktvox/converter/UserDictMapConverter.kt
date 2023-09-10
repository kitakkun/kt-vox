package com.github.kitakkun.ktvox.converter

import com.github.kitakkun.ktvox.schema.dictionary.UserDictWord
import com.github.kitakkun.ktvox.schema.dictionary.UserDictWordMap
import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.converter.Converter
import de.jensklingenberg.ktorfit.internal.TypeData
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement

/**
 * Delete this class after this issue is fixed.
 * https://github.com/Foso/Ktorfit/issues/399
 */
class UserDictMapConverterFactory : Converter.Factory {
    override fun suspendResponseConverter(
        typeData: TypeData,
        ktorfit: Ktorfit
    ): Converter.SuspendResponseConverter<HttpResponse, *>? {
        if (typeData.typeInfo.type == UserDictWordMap::class) {
            return UserDictMapConverter()
        }
        return super.suspendResponseConverter(typeData, ktorfit)
    }
}

class UserDictMapConverter : Converter.SuspendResponseConverter<HttpResponse, UserDictWordMap> {
    /**
     * example response:
     * {
     *   "property1": {
     *     "surface": "string",
     *     "priority": 10,
     *     "context_id": 1348,
     *     "part_of_speech": "string",
     *     "part_of_speech_detail_1": "string",
     *     "part_of_speech_detail_2": "string",
     *     "part_of_speech_detail_3": "string",
     *     "inflectional_type": "string",
     *     "inflectional_form": "string",
     *     "stem": "string",
     *     "yomi": "string",
     *     "pronunciation": "string",
     *     "accent_type": 0,
     *     "mora_count": 0,
     *     "accent_associative_rule": "string"
     *   },
     *   "property2": {
     *     "surface": "string",
     *     "priority": 10,
     *     "context_id": 1348,
     *     "part_of_speech": "string",
     *     "part_of_speech_detail_1": "string",
     *     "part_of_speech_detail_2": "string",
     *     "part_of_speech_detail_3": "string",
     *     "inflectional_type": "string",
     *     "inflectional_form": "string",
     *     "stem": "string",
     *     "yomi": "string",
     *     "pronunciation": "string",
     *     "accent_type": 0,
     *     "mora_count": 0,
     *     "accent_associative_rule": "string"
     *   }
     * }
     */
    override suspend fun convert(response: HttpResponse): UserDictWordMap {
        val text = response.bodyAsText()
        return convert(rawText = text)
    }

    /**
     * This method is visible for testing.
     */
    fun convert(rawText: String): UserDictWordMap {
        val jsonElement = Json.parseToJsonElement(rawText)

        if (jsonElement !is JsonObject) {
            return UserDictWordMap(properties = emptyMap())
        }

        return UserDictWordMap(
            properties = jsonElement.entries.associate { (key, value) ->
                val userDictWord = Json.decodeFromJsonElement<UserDictWord>(value)
                key to userDictWord
            }
        )
    }
}
