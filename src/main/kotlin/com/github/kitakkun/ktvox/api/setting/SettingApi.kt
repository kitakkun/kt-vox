package com.github.kitakkun.ktvox.api.setting

import com.github.kitakkun.ktvox.annotation.ExperimentalKtVoxApi
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SettingApi {
    @ExperimentalKtVoxApi
    @GET("/setting")
    suspend fun getSetting(): Response<String>

    @ExperimentalKtVoxApi
    @POST("/setting")
    suspend fun postSetting(
        @Body requestBody: RequestBody,
    ): Response<String>
}
