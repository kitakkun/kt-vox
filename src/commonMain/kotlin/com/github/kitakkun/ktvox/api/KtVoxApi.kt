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
        /**
         * create an instance of KtVoxApi from url
         * @param serverUrl url of VOICEVOX server
         */
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

    /**
     * create an audio query for synthesis
     * @param text text to synthesize
     * @param speaker speaker id
     * @param coreVersion core version
     * @return audio query
     */
    @POST("audio_query")
    suspend fun createAudioQuery(
        @Query("text") text: String,
        @Query("speaker") speaker: Int,
        @Query("core_version") coreVersion: String? = null,
    ): AudioQuery

    /**
     * create an audio query for synthesis from preset
     * @param text text to synthesize
     * @param presetId preset id
     * @param coreVersion core version
     * @return audio query
     */
    @POST("audio_query_from_preset")
    suspend fun createAudioQueryFromPreset(
        @Query("text") text: String,
        @Query("preset_id") presetId: Int,
        @Query("core_version") coreVersion: String? = null,
    ): AudioQuery

    /**
     * get accents from text
     * @param text text to get accents
     * @param speaker speaker id
     * @param isKana whether text is kana
     * @param coreVersion core version
     * @return list of accent phrases
     */
    @POST("accent_phrases")
    suspend fun getAccentPhrases(
        @Query("text") text: String,
        @Query("speaker") speaker: Int,
        @Query("is_kana") isKana: Boolean = false,
        @Query("core_version") coreVersion: String? = null,
    ): List<AccentPhrase>

    /**
     * get mora data from accent phrases
     * @param speaker speaker id
     * @param coreVersion core version
     * @param accentPhrases list of accent phrases
     * @return list of accent phrases
     */
    @POST("mora_data")
    suspend fun getMoraData(
        @Query("speaker") speaker: Int,
        @Query("core_version") coreVersion: String? = null,
        @Body accentPhrases: List<AccentPhrase>,
        @Header("Content-Type") contentType: String = "application/json",
    ): List<AccentPhrase>

    /**
     * get mora length from accent phrases
     * @param speaker speaker id
     * @param coreVersion core version
     * @param accentPhrases list of accent phrases
     * @return list of accent phrases
     */
    @POST("mora_length")
    suspend fun getMoraLength(
        @Query("speaker") speaker: Int,
        @Query("core_version") coreVersion: String? = null,
        @Body accentPhrases: List<AccentPhrase>,
        @Header("Content-Type") contentType: String = "application/json",
    ): List<AccentPhrase>

    /**
     * get mora pitch from accent phrases
     * @param speaker speaker id
     * @param coreVersion core version
     * @param accentPhrases list of accent phrases
     * @return list of accent phrases
     */
    @POST("mora_pitch")
    suspend fun getMoraPitch(
        @Query("speaker") speaker: Int,
        @Query("core_version") coreVersion: String? = null,
        @Body accentPhrases: List<AccentPhrase>,
        @Header("Content-Type") contentType: String = "application/json",
    ): List<AccentPhrase>

    /**
     * get setting
     * @return setting
     */
    @GET("setting")
    suspend fun getSetting(): String

    /**
     * post setting
     * @param corsPolicyMode cors policy mode
     * @param allowOrigin list of allow origin
     * @return setting
     */
    @FormUrlEncoded
    @POST("setting")
    suspend fun postSetting(
        @Field("cors_policy_mode") corsPolicyMode: String,
        @Field("allow_origin") allowOrigin: List<String>,
    ): String

    /**
     * get synthesized audio
     * @param speaker speaker id
     * @param enableInterrogativeUpspeak whether enable interrogative upspeak.
     *   if true, the end of the sentence is pronounced like a question phrase.
     * @param coreVersion core version
     * @param audioQuery audio query
     * @return synthesized audio bytes
     */
    @POST("synthesis")
    suspend fun postSynthesis(
        @Query("speaker") speaker: Int,
        @Query("enable_interrogative_upspeak") enableInterrogativeUpspeak: Boolean = true,
        @Query("core_version") coreVersion: String? = null,
        @Body audioQuery: AudioQuery,
        @Header("Content-Type") contentType: String = "application/json",
    ): ByteArray

    /**
     * get synthesized audio (cancellable)
     * @param speaker speaker id
     * @param coreVersion core version
     * @param audioQuery audio query
     * @return synthesized audio bytes
     */
    @POST("cancellable_synthesis")
    fun postCancellableSynthesis(
        @Query("speaker") speaker: Int,
        @Query("core_version") coreVersion: String? = null,
        @Body audioQuery: AudioQuery,
    ): Call<String>

    /**
     * get synthesized audio (multiple audio queries)
     * @param speaker speaker id
     * @param coreVersion core version
     * @param audioQueries list of audio queries
     * @return synthesized audio bytes
     */
    @POST("multi_synthesis")
    suspend fun postMultiSynthesis(
        @Query("speaker") speaker: Int,
        @Query("core_version") coreVersion: String? = null,
        @Body audioQueries: List<AudioQuery>,
        @Header("Content-Type") contentType: String = "application/json",
    ): ByteArray

    /**
     * check whether morphing is available for speakers
     * @param coreVersion core version
     * @param baseSpeakers list of speaker ids
     * @return list of morphable target infos
     */
    @POST("morphable_targets")
    suspend fun postMorphableTargets(
        @Query("core_version") coreVersion: String? = null,
        @Body baseSpeakers: List<Int>,
        @Header("Content-Type") contentType: String = "application/json",
    ): MorphableTargetInfos

    /**
     * get synthesized audio (morphing)
     * @param baseSpeaker base speaker id
     * @param targetSpeaker target speaker id
     * @param morphRate morph rate. 0.0(base) ~ 1.0(target).
     * @param coreVersion core version
     * @param audioQuery audio query
     * @return synthesized audio bytes
     */
    @POST("synthesis_morphing")
    suspend fun postSynthesisMorphing(
        @Query("base_speaker") baseSpeaker: Int,
        @Query("target_speaker") targetSpeaker: Int,
        @Query("morph_rate") morphRate: Double,
        @Query("core_version") coreVersion: String? = null,
        @Body audioQuery: AudioQuery,
        @Header("Content-Type") contentType: String = "application/json",
    ): ByteArray

    /**
     * connect multiple wav data
     * @param waves list of wav data
     * @return connected wav data
     */
    @POST("connect_waves")
    suspend fun connectWaves(
        @Body waves: List<ByteArray>,
        @Header("Content-Type") contentType: String = "application/json",
    ): ByteArray

    /**
     * get version of VOICEVOX engine
     * @return version
     */
    @GET("version")
    suspend fun getVersion(): String

    /**
     * get core versions
     * @return list of core versions
     */
    @GET("core_versions")
    suspend fun getCoreVersions(): List<String>

    /**
     * get speakers
     * @return list of speakers
     */
    @GET("speakers")
    suspend fun getSpeakers(): List<Speaker>

    /**
     * get speaker info
     * @param speakerUuid speaker uuid
     * @param coreVersion core version
     * @return speaker info
     */
    @GET("speaker_info")
    suspend fun getSpeakerInfo(
        @Query("speaker_uuid") speakerUuid: String,
        @Query("core_version") coreVersion: String? = null,
        @Header("Content-Type") contentType: String = "application/json"
    ): SpeakerInfo

    /**
     * get downloadable libraries
     * @return list of downloadable libraries
     */
    @GET("downloadable_libraries")
    suspend fun getDownloadableLibraries(): List<DownloadableLibrary>

    /**
     * get presets
     * @return list of presets
     */
    @GET("presets")
    suspend fun getPresets(): List<Preset>

    /**
     * add preset
     * @param preset preset
     * @return preset id
     */
    @POST("add_preset")
    suspend fun addPreset(
        @Body preset: Preset,
        @Header("Content-Type") contentType: String = "application/json"
    ): Int

    /**
     * update preset
     * @param preset preset
     * @return preset id
     */
    @POST("update_preset")
    suspend fun updatePreset(
        @Body preset: Preset,
        @Header("Content-Type") contentType: String = "application/json"
    ): Int

    /**
     * delete preset
     * @param presetId preset id
     */
    @POST("delete_preset")
    suspend fun deletePreset(
        @Query("id") presetId: Int,
        @Header("Content-Type") contentType: String = "application/json"
    )

    /**
     * get whether speaker is initialized or not
     * @param speaker speaker id
     * @param coreVersion core version
     * @return whether speaker is initialized (initialized: true, not initialized: false)
     */
    @GET("is_initialized_speaker")
    suspend fun getIsInitializedSpeaker(
        @Query("speaker") speaker: Int,
        @Query("core_version") coreVersion: String? = null,
    ): Boolean

    /**
     * initialize speaker
     * @param speaker speaker id
     * @param skipReinit whether skip re-initialization
     * @param coreVersion core version
     */
    @POST("initialize_speaker")
    suspend fun initializeSpeaker(
        @Query("speaker") speaker: Int,
        @Query("skip_reinit") skipReinit: Boolean = false,
        @Query("core_version") coreVersion: String? = null,
    ): Unit

    /**
     * get supported devices (cpu, cuda, dml)
     * @return device support
     */
    @GET("supported_devices")
    suspend fun getSupportedDevices(): DeviceSupport

    /**
     * get engine manifest
     * @return engine manifest
     */
    @GET("engine_manifest")
    suspend fun getEngineManifest(): EngineManifest

    /**
     * get user dictionary
     * @return user dictionary
     */
    @GET("user_dict")
    suspend fun getUserDict(): UserDictWordMap

    /**
     * add user dictionary word
     * @param surface word surface
     * @param pronunciation word pronunciation
     * @param accentType word accent type
     * @param wordType word type
     * @param priority word priority
     * @return word uuid
     */
    @POST("user_dict_word")
    suspend fun addUserDictWord(
        @Query("surface") surface: String,
        @Query("pronunciation") pronunciation: String,
        @Query("accent_type") accentType: Int,
        @Query("word_type") wordType: String? = null,
        @Query("priority") priority: Int? = null,
    ): String

    /**
     * rewrite user dictionary word
     * @param wordUuid word uuid
     * @param surface word surface
     * @param pronunciation word pronunciation
     * @param accentType word accent type
     * @param wordType word type
     * @param priority word priority
     */
    @PUT("user_dict_word/{word_uuid}")
    suspend fun rewriteUserDictWord(
        @Path("word_uuid") wordUuid: String,
        @Query("surface") surface: String,
        @Query("pronunciation") pronunciation: String,
        @Query("accent_type") accentType: Int,
        @Query("word_type") wordType: String? = null,
        @Query("priority") priority: Int? = null,
    )

    /**
     * delete user dictionary word
     * @param wordUuid word uuid
     */
    @DELETE("user_dict_word/{word_uuid}")
    suspend fun deleteUserDictWord(
        @Path("word_uuid") wordUuid: String,
    )

    /**
     * import user dictionary
     * @param override whether override user dictionary
     * @param importDictData user dictionary data
     */
    @POST("import_user_dict")
    suspend fun importUserDict(
        @Query("override") override: Boolean,
        @Body importDictData: Map<String, UserDictWord>,
        @Header("Content-Type") contentType: String = "application/json",
    )
}
