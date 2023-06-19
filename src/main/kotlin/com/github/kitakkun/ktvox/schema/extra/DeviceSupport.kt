package com.github.kitakkun.ktvox.schema.extra

import kotlinx.serialization.Serializable

@Serializable
data class DeviceSupport(
    val cpu: Boolean,
    val cuda: Boolean,
    val dml: Boolean,
)
