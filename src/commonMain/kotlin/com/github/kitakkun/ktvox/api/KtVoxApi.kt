package com.github.kitakkun.ktvox.api

import com.github.kitakkun.ktvox.converter.MorphableTargetInfosConverterFactory
import com.github.kitakkun.ktvox.converter.UserDictMapConverterFactory
import com.github.kitakkun.ktvox.schema.dictionary.UserDictWord
import com.github.kitakkun.ktvox.schema.dictionary.UserDictWordMap
import com.github.kitakkun.ktvox.schema.extra.DeviceSupport
import com.github.kitakkun.ktvox.schema.extra.DownloadableLibrary
import com.github.kitakkun.ktvox.schema.extra.EngineManifest
import com.github.kitakkun.ktvox.schema.extra.Preset
import com.github.kitakkun.ktvox.schema.extra.Speaker
import com.github.kitakkun.ktvox.schema.extra.SpeakerInfo
import com.github.kitakkun.ktvox.schema.query.AccentPhrase
import com.github.kitakkun.ktvox.schema.query.AudioQuery
import com.github.kitakkun.ktvox.schema.synth.MorphableTargetInfos
import de.jensklingenberg.ktorfit.Call
import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.converter.builtin.CallConverterFactory
import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.DELETE
import de.jensklingenberg.ktorfit.http.Field
import de.jensklingenberg.ktorfit.http.FormUrlEncoded
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Header
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.PUT
import de.jensklingenberg.ktorfit.http.Path
import de.jensklingenberg.ktorfit.http.Query
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

interface KtVoxApi {
    companion object {
        fun initialize(serverUrl: String): KtVoxApi {
            val baseUrl = if (serverUrl.endsWith("/")) {
                serverUrl
            } else {
                "$serverUrl/"
            }
            val httpClient = HttpClient {
                install(ContentNegotiation) {
                    json(Json {
                        isLenient = true
                        ignoreUnknownKeys = true
                    })
                }
            }
            val ktorfit = Ktorfit.Builder()
                .httpClient(httpClient)
                .baseUrl(baseUrl)
                .converterFactories(
                    CallConverterFactory(),
                    UserDictMapConverterFactory(),
                    MorphableTargetInfosConverterFactory(),
                )
                .build()
            return ktorfit.create()
        }
    }

    @POST("audio_query")
    suspend fun createAudioQuery(
        @Query("text") text: String,
        @Query("speaker") speaker: Int,
        @Query("core_version") coreVersion: String? = null,
    ): AudioQuery

    @POST("audio_query_from_preset")
    suspend fun createAudioQueryFromPreset(
        @Query("text") text: String,
        @Query("preset_id") presetId: Int,
        @Query("core_version") coreVersion: String? = null,
    ): AudioQuery

    @POST("accent_phrases")
    suspend fun getAccentPhrases(
        @Query("text") text: String,
        @Query("speaker") speaker: Int,
        @Query("is_kana") isKana: Boolean = false,
        @Query("core_version") coreVersion: String? = null,
    ): List<AccentPhrase>

    @POST("mora_data")
    suspend fun getMoraData(
        @Query("speaker") speaker: Int,
        @Query("core_version") coreVersion: String? = null,
        @Body accentPhrases: List<AccentPhrase>,
        @Header("Content-Type") contentType: String = "application/json",
    ): List<AccentPhrase>

    @POST("mora_length")
    suspend fun getMoraLength(
        @Query("speaker") speaker: Int,
        @Query("core_version") coreVersion: String? = null,
        @Body accentPhrases: List<AccentPhrase>,
        @Header("Content-Type") contentType: String = "application/json",
    ): List<AccentPhrase>

    @POST("mora_pitch")
    suspend fun getMoraPitch(
        @Query("speaker") speaker: Int,
        @Query("core_version") coreVersion: String? = null,
        @Body accentPhrases: List<AccentPhrase>,
        @Header("Content-Type") contentType: String = "application/json",
    ): List<AccentPhrase>

    @GET("setting")
    suspend fun getSetting(): String

    @FormUrlEncoded
    @POST("setting")
    suspend fun postSetting(
        @Field("cors_policy_mode") corsPolicyMode: String,
        @Field("allow_origin") allowOrigin: List<String>,
    ): String

    @POST("synthesis")
    suspend fun postSynthesis(
        @Query("speaker") speaker: Int,
        @Query("enable_interrogative_upspeak") enableInterrogativeUpspeak: Boolean = true,
        @Query("core_version") coreVersion: String? = null,
        @Body audioQuery: AudioQuery,
        @Header("Content-Type") contentType: String = "application/json",
    ): ByteArray

    @POST("cancellable_synthesis")
    fun postCancellableSynthesis(
        @Query("speaker") speaker: Int,
        @Query("core_version") coreVersion: String? = null,
        @Body audioQuery: AudioQuery,
    ): Call<String>

    @POST("multi_synthesis")
    suspend fun postMultiSynthesis(
        @Query("speaker") speaker: Int,
        @Query("core_version") coreVersion: String? = null,
        @Body audioQueries: List<AudioQuery>,
        @Header("Content-Type") contentType: String = "application/json",
    ): ByteArray

    @POST("morphable_targets")
    suspend fun postMorphableTargets(
        @Query("core_version") coreVersion: String? = null,
        @Body baseSpeakers: List<Int>,
        @Header("Content-Type") contentType: String = "application/json",
    ): MorphableTargetInfos

    @POST("synthesis_morphing")
    suspend fun postSynthesisMorphing(
        @Query("base_speaker") baseSpeaker: Int,
        @Query("target_speaker") targetSpeaker: Int,
        @Query("morph_rate") morphRate: Double,
        @Query("core_version") coreVersion: String? = null,
        @Body audioQuery: AudioQuery,
        @Header("Content-Type") contentType: String = "application/json",
    ): ByteArray

    @POST("connect_waves")
    suspend fun connectWaves(
        @Body waves: List<ByteArray>,
        @Header("Content-Type") contentType: String = "application/json",
    ): ByteArray

    @GET("version")
    suspend fun getVersion(): String

    @GET("core_versions")
    suspend fun getCoreVersions(): List<String>

    @GET("speakers")
    suspend fun getSpeakers(): List<Speaker>

    @GET("speaker_info")
    suspend fun getSpeakerInfo(
        @Query("speaker_uuid") speakerUuid: String,
        @Query("core_version") coreVersion: String? = null,
        @Header("Content-Type") contentType: String = "application/json"
    ): SpeakerInfo

    @GET("downloadable_libraries")
    suspend fun getDownloadableLibraries(): List<DownloadableLibrary>

    @GET("presets")
    suspend fun getPresets(): List<Preset>

    @POST("add_preset")
    suspend fun addPreset(
        @Body preset: Preset,
        @Header("Content-Type") contentType: String = "application/json"
    ): Int

    @POST("update_preset")
    suspend fun updatePreset(
        @Body preset: Preset,
        @Header("Content-Type") contentType: String = "application/json"
    ): Int

    @POST("delete_preset")
    suspend fun deletePreset(
        @Query("id") presetId: Int,
        @Header("Content-Type") contentType: String = "application/json"
    )

    @GET("is_initialized_speaker")
    suspend fun getIsInitializedSpeaker(
        @Query("speaker") speaker: Int,
        @Query("core_version") coreVersion: String? = null,
    ): Boolean

    @POST("initialize_speaker")
    suspend fun initializeSpeaker(
        @Query("speaker") speaker: Int,
        @Query("skip_reinit") skipReinit: Boolean = false,
        @Query("core_version") coreVersion: String? = null,
    ): Unit

    @GET("supported_devices")
    suspend fun getSupportedDevices(): DeviceSupport

    @GET("engine_manifest")
    suspend fun getEngineManifest(): EngineManifest

    @GET("user_dict")
    suspend fun getUserDict(): UserDictWordMap

    @POST("user_dict_word")
    suspend fun addUserDictWord(
        @Query("surface") surface: String,
        @Query("pronunciation") pronunciation: String,
        @Query("accent_type") accentType: Int,
        @Query("word_type") wordType: String? = null,
        @Query("priority") priority: Int? = null,
    ): String

    @PUT("user_dict_word/{word_uuid}")
    suspend fun rewriteUserDictWord(
        @Path("word_uuid") wordUuid: String,
        @Query("surface") surface: String,
        @Query("pronunciation") pronunciation: String,
        @Query("accent_type") accentType: Int,
        @Query("word_type") wordType: String? = null,
        @Query("priority") priority: Int? = null,
    )

    @DELETE("user_dict_word/{word_uuid}")
    suspend fun deleteUserDictWord(
        @Path("word_uuid") wordUuid: String,
    )

    @POST("import_user_dict")
    suspend fun importUserDict(
        @Query("override") override: Boolean,
        @Body importDictData: Map<String, UserDictWord>,
        @Header("Content-Type") contentType: String = "application/json",
    )
}
