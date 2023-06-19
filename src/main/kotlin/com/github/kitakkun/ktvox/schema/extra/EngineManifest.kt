package com.github.kitakkun.ktvox.schema.extra

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class EngineManifest(
    @SerializedName("manifest_version")
    val manifestVersion: String,
    val name: String,
    val brandName: String,
    val uuid: String,
    val url: String,
    val icon: String,
    @SerializedName("default_sampling_rate")
    val defaultSamplingRate: Int,
    @SerializedName("terms_of_service")
    val termsOfService: String,
    @SerializedName("update_infos")
    val updateInfos: List<UpdateInfo>,
    @SerializedName("dependency_licenses")
    val dependencyLicenses: List<LicenseInfo>,
    @SerializedName("downloadable_libraries_path")
    val downloadableLibrariesPath: String?,
    @SerializedName("downloadable_libraries_url")
    val downloadableLibrariesUrl: String?,
    @SerializedName("supported_features")
    val supportedFeatures: SupportedFeatures,
)

@Serializable
data class UpdateInfo(
    val version: String,
    val descriptions: List<String>,
    val contributors: List<String>,
)

@Serializable
data class LicenseInfo(
    val name: String,
    val version: String?,
    val license: String?,
    val text: String,
)