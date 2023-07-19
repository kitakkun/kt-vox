package com.github.kitakkun.ktvox.api.synth

import com.github.kitakkun.ktvox.annotation.ExperimentalKtVoxApi
import com.github.kitakkun.ktvox.schema.query.AudioQuery
import com.github.kitakkun.ktvox.schema.synth.MorphableState
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface SynthApi {
    @POST("/synthesis")
    suspend fun postSynthesis(
        @Query("speaker") speaker: Int,
        @Query("enable_interrogative_upspeak") enableInterrogativeUpspeak: Boolean = true,
        @Query("core_version") coreVersion: String? = null,
        @Body audioQuery: AudioQuery,
    ): Response<ResponseBody>

    @ExperimentalKtVoxApi
    @POST("/cancellable_synthesis")
    fun postCancellableSynthesis(
        @Query("speaker") speaker: Int,
        @Query("core_version") coreVersion: String? = null,
        @Body audioQuery: AudioQuery,
    ): Call<ResponseBody>

    @POST("/multi_synthesis")
    suspend fun postMultiSynthesis(
        @Query("speaker") speaker: Int,
        @Query("core_version") coreVersion: String? = null,
        @Body audioQueries: List<AudioQuery>,
    ): Response<ResponseBody>

    @POST("/morphable_targets")
    suspend fun postMorphableTargets(
        @Query("core_version") coreVersion: String? = null,
        @Body baseSpeakers: List<Int>,
    ): Response<List<Map<Int, MorphableState>>>

    @POST("/synthesis_morphing")
    suspend fun postSynthesisMorphing(
        @Query("base_speaker") baseSpeaker: Int,
        @Query("target_speaker") targetSpeaker: Int,
        @Query("morph_rate") morphRate: Double,
        @Query("core_version") coreVersion: String? = null,
        @Body audioQuery: AudioQuery,
    ): Response<ResponseBody>
}
