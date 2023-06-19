package com.github.kitakkun.ktvox.api.dictionary

import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

// TODO
interface DictApi {
    @GET("/user_dict")
    suspend fun getUserDict(): String

    @POST("/user_dict_word")
    suspend fun addUserDictWord(): String

    @PUT("/user_dict_word/{word_uuid}")
    suspend fun updateUserDictWord(): String

    @DELETE("/user_dict_word/{word_uuid}")
    suspend fun deleteUserDictWord(): String

    @POST("/import_user_dict")
    suspend fun importUserDict(): String
}