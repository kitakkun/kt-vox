package com.github.kitakkun.ktvox.api.dictionary

import com.github.kitakkun.ktvox.schema.dictionary.UserDictWord
import retrofit2.Response
import retrofit2.http.*

interface DictApi {
    @GET("/user_dict")
    suspend fun getUserDict(): Response<Map<String, UserDictWord>>

    @POST("/user_dict_word")
    suspend fun addUserDictWord(
        @Query("surface") surface: String,
        @Query("pronunciation") pronunciation: String,
        @Query("accent_type") accentType: Int,
        @Query("word_type") wordType: String? = null,
        @Query("priority") priority: Int? = null,
    ): Response<String>

    @PUT("/user_dict_word/{word_uuid}")
    suspend fun rewriteUserDictWord(
        @Path("word_uuid") wordUuid: String,
        @Query("surface") surface: String,
        @Query("pronunciation") pronunciation: String,
        @Query("accent_type") accentType: Int,
        @Query("word_type") wordType: String? = null,
        @Query("priority") priority: Int? = null,
    ): Response<Unit>

    @DELETE("/user_dict_word/{word_uuid}")
    suspend fun deleteUserDictWord(
        @Path("word_uuid") wordUuid: String,
    ): Response<Unit>

    @POST("/import_user_dict")
    suspend fun importUserDict(
        @Query("override") override: Boolean,
        @Body importDictData: Map<String, UserDictWord>,
    ): Response<Unit>
}
