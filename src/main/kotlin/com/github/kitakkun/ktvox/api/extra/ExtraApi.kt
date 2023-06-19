package com.github.kitakkun.ktvox.api.extra

import com.github.kitakkun.ktvox.schema.*
import com.github.kitakkun.ktvox.schema.extra.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ExtraApi {
    @POST("/connect_waves")
    suspend fun connectWaves(
        @Body waves: List<String>,
    ): Response<String>

    @GET("/version")
    suspend fun getVersion(): Response<String>

    @GET("/core_versions")
    suspend fun getCoreVersions(): Response<List<String>>

    @GET("/speakers")
    suspend fun getSpeakers(): Response<List<Speaker>>

    @GET("/speaker_info")
    suspend fun getSpeakerInfo(
        @Query("speaker_uuid") speakerUuid: String,
        @Query("core_version") coreVersion: String? = null,
    ): Response<SpeakerInfo>


    @GET("/downloadable_libraries")
    suspend fun getDownloadableLibraries(): Response<List<DownloadableLibrary>>

    @GET("/presets")
    suspend fun getPresets(): Response<List<Preset>>

    @POST("/add_preset")
    suspend fun addPreset(
        @Body preset: Preset,
    ): Response<Int>

    @POST("/update_preset")
    suspend fun updatePreset(
        @Body preset: Preset,
    ): Response<Int>

    @POST("/delete_preset")
    suspend fun deletePreset(
        @Query("id") presetId: Int,
    ): Response<Int>

    @GET("/is_initialized_speaker")
    suspend fun getIsInitializedSpeaker(
        @Query("speaker") speaker: Int,
        @Query("core_version") coreVersion: String? = null,
    ): Response<Boolean>

    @POST("/initialize_speaker")
    suspend fun initializeSpeaker(
        @Query("speaker") speaker: Int,
        @Query("skip_reinit") skipReinit: Boolean = false,
        @Query("core_version") coreVersion: String? = null,
    ): Response<Unit>

    @GET("/supported_devices")
    suspend fun getSupportedDevices(): Response<DeviceSupport>

    @GET("/engine_manifest")
    suspend fun getEngineManifest(): Response<EngineManifest>
}
