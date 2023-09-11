package com.github.kitakkun.ktvox.schema.synth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MorphableState(
    @SerialName("is_morphable")
    val isMorphable: Boolean,
)
