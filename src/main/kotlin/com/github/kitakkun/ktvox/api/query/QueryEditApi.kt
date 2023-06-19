package com.github.kitakkun.ktvox.api.query

import com.github.kitakkun.ktvox.schema.query.AccentPhrase
import com.github.kitakkun.ktvox.schema.query.Mora
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface QueryEditApi {
    @POST("/accent_phrases")
    suspend fun getAccentPhrases(
        @Query("text") text: String,
        @Query("speaker") speaker: Int,
        @Query("is_kana") isKana: Boolean = false,
        @Query("core_version") coreVersion: String? = null,
    ): Response<List<AccentPhrase>>

    @POST("/mora_data")
    suspend fun getMoraData(
        @Header("Content-Type") contentType: String = "application/json",
        @Query("speaker") speaker: Int,
        @Query("core_version") coreVersion: String? = null,
        @Body accentPhrases: List<AccentPhrase>,
    ): Response<List<Mora>>

    @POST("/mora_length")
    suspend fun getMoraLength(
        @Query("speaker") speaker: Int,
        @Query("core_version") coreVersion: String? = null,
        @Body accentPhrases: List<AccentPhrase>,
    ): Response<List<Mora>>

    @POST("/mora_pitch")
    suspend fun getMoraPitch(
        @Query("speaker") speaker: Int,
        @Query("core_version") coreVersion: String? = null,
        @Body accentPhrases: List<AccentPhrase>,
    ): Response<List<Mora>>

}