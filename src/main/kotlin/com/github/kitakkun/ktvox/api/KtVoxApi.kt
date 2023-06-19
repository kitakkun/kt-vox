package com.github.kitakkun.ktvox.api

import com.github.kitakkun.ktvox.api.extra.ExtraApi
import com.github.kitakkun.ktvox.api.query.QueryApi
import com.github.kitakkun.ktvox.api.synth.SynthApi

interface KtVoxApi : QueryApi, SynthApi, ExtraApi