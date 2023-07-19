package com.github.kitakkun.ktvox.api.setting

import com.github.kitakkun.ktvox.annotation.ExperimentalKtVoxApi
import retrofit2.Response
import retrofit2.http.*

interface SettingApi {
    @ExperimentalKtVoxApi
    @GET("/setting")
    suspend fun getSetting(): Response<String>

    @ExperimentalKtVoxApi
    @FormUrlEncoded
    @POST("/setting")
    suspend fun postSetting(
        @Field("cors_policy_mode") corsPolicyMode: String,
        @Field("allow_origin") allowOrigin: List<String>,
    ): Response<String>
}
