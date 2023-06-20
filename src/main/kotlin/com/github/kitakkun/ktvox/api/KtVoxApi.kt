package com.github.kitakkun.ktvox.api

import com.github.kitakkun.ktvox.api.dictionary.DictApi
import com.github.kitakkun.ktvox.api.extra.ExtraApi
import com.github.kitakkun.ktvox.api.query.QueryApi
import com.github.kitakkun.ktvox.api.synth.SynthApi
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

interface KtVoxApi : QueryApi, SynthApi, ExtraApi, DictApi {
    companion object {
        fun initialize(serverUrl: String): KtVoxApi {
            val retrofit = retrofit2.Retrofit.Builder()
                .baseUrl(serverUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(KtVoxApi::class.java)
        }
    }
}
