package com.github.kitakkun.ktvox.converter

import com.github.kitakkun.ktvox.schema.synth.MorphableState
import com.github.kitakkun.ktvox.schema.synth.MorphableTargetInfos
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class MorphableTargetInfosConverterTest {
    @Test
    fun testConvertSample() = runTest {
        val text = """
            [
              {
                "property1": {
                  "is_morphable": true
                },
                "property2": {
                  "is_morphable": true
                }
              }
            ]
        """.trimIndent()
        val converter = MorphableTargetInfosConverter()
        val actual = converter.convert(text)
        assertEquals(
            expected = MorphableTargetInfos(
                listOf(
                    mapOf(
                        "property1" to MorphableState(isMorphable = true),
                        "property2" to MorphableState(isMorphable = true),
                    )
                )
            ),
            actual = actual,
        )
    }
}
