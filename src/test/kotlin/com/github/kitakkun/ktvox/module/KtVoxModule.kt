package com.github.kitakkun.ktvox.module

import com.github.kitakkun.ktvox.api.extra.ExtraApi
import com.github.kitakkun.ktvox.api.query.QueryApi
import com.github.kitakkun.ktvox.api.query.QueryCreateApi
import com.github.kitakkun.ktvox.api.query.QueryEditApi
import com.github.kitakkun.ktvox.api.synth.SynthApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create

val ktVoxModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("http://127.0.0.1:50021")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    factory<QueryApi> { get<Retrofit>().create() }
    factory<QueryEditApi> { get<Retrofit>().create() }
    factory<QueryCreateApi> { get<Retrofit>().create() }
    factory<SynthApi> { get<Retrofit>().create() }
    factory<ExtraApi> { get<Retrofit>().create() }
}
