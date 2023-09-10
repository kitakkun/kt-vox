package com.github.kitakkun.ktvox.schema.extra

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EngineManifest(
    @SerialName("manifest_version")
    val manifestVersion: String,
    val name: String,
    @SerialName("brand_name")
    val brandName: String,
    val uuid: String,
    val url: String,
    val icon: String,
    @SerialName("default_sampling_rate")
    val defaultSamplingRate: Int,
    @SerialName("terms_of_service")
    val termsOfService: String,
    @SerialName("update_infos")
    val updateInfos: List<UpdateInfo>,
    @SerialName("dependency_licenses")
    val dependencyLicenses: List<LicenseInfo>,
    @SerialName("supported_vvlib_manifest_version")
    val supportedVvlibManifestVersion: String? = null,
    @SerialName("supported_features")
    val supportedFeatures: SupportedFeatures,
)

@Serializable
data class UpdateInfo(
    val version: String,
    val descriptions: List<String>,
    val contributors: List<String>?,
)

@Serializable
data class LicenseInfo(
    val name: String,
    val version: String?,
    val license: String?,
    val text: String,
)
