package com.github.kitakkun.ktvox.schema.synth

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class MorphableState(
    @SerializedName("is_morphable")
    val isMorphable: Boolean,
)
