package com.github.kitakkun.ktvox.converter

import com.github.kitakkun.ktvox.schema.synth.MorphableState
import com.github.kitakkun.ktvox.schema.synth.MorphableTargetInfos
import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.converter.Converter
import de.jensklingenberg.ktorfit.internal.TypeData
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

/**
 * Delete this class after this issue is fixed.
 * https://github.com/Foso/Ktorfit/issues/399
 */
class MorphableTargetInfosConverterFactory : Converter.Factory {
    override fun suspendResponseConverter(
        typeData: TypeData,
        ktorfit: Ktorfit
    ): Converter.SuspendResponseConverter<HttpResponse, *>? {
        if (typeData.typeInfo.type == MorphableTargetInfos::class) {
            return MorphableTargetInfosConverter()
        }
        return super.suspendResponseConverter(typeData, ktorfit)
    }
}

class MorphableTargetInfosConverter :
    Converter.SuspendResponseConverter<HttpResponse, MorphableTargetInfos> {

    /**
     * example response:
     * [
     *   {
     *     "property1": {
     *       "is_morphable": true
     *     },
     *     "property2": {
     *       "is_morphable": true
     *     }
     *   }
     * ]
     */
    override suspend fun convert(response: HttpResponse): MorphableTargetInfos {
        val text = response.bodyAsText()
        return convert(text)
    }

    /**
     * this method is visible for testing.
     */
    fun convert(rawText: String): MorphableTargetInfos {
        val json = Json.parseToJsonElement(rawText)

        return MorphableTargetInfos(
            json.jsonArray.map { element ->
                element.jsonObject.entries.map { (key, value) ->
                    key to Json.decodeFromJsonElement<MorphableState>(value)
                }.toMap()
            }
        )
    }
}
