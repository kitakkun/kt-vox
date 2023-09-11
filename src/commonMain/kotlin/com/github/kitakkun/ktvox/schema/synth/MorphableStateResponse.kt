package com.github.kitakkun.ktvox.schema.synth

import kotlinx.serialization.Serializable

@Serializable
data class MorphableTargetInfos(
    val value: List<Map<String, MorphableState>>
)
