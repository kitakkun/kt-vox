package com.github.kitakkun.ktvox.api.query

import com.github.kitakkun.ktvox.schema.query.AudioQuery
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface QueryCreateApi {
    @POST("/audio_query")
    suspend fun createAudioQuery(
        @Query("text") text: String,
        @Query("speaker") speaker: Int,
        @Query("core_version") coreVersion: String? = null,
    ): Response<AudioQuery>

    @POST("/audio_query_from_preset")
    suspend fun createAudioQueryFromPreset(
        @Query("text") text: String,
        @Query("preset_id") presetId: Int,
        @Query("core_version") coreVersion: String? = null,
    ): Response<AudioQuery>

}