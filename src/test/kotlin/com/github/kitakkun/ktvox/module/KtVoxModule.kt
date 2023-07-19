package com.github.kitakkun.ktvox.module

import com.github.kitakkun.ktvox.api.dictionary.DictApi
import com.github.kitakkun.ktvox.api.extra.ExtraApi
import com.github.kitakkun.ktvox.api.query.QueryApi
import com.github.kitakkun.ktvox.api.query.QueryCreateApi
import com.github.kitakkun.ktvox.api.query.QueryEditApi
import com.github.kitakkun.ktvox.api.setting.SettingApi
import com.github.kitakkun.ktvox.api.synth.SynthApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create

fun ktVoxModule(baseUrl: String) = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    factory<QueryApi> { get<Retrofit>().create() }
    factory<QueryEditApi> { get<Retrofit>().create() }
    factory<QueryCreateApi> { get<Retrofit>().create() }
    factory<SynthApi> { get<Retrofit>().create() }
    factory<ExtraApi> { get<Retrofit>().create() }
    factory<DictApi> { get<Retrofit>().create() }
    factory<SettingApi> { get<Retrofit>().create() }
}
