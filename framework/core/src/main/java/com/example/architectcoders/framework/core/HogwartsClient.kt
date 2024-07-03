package com.example.architectcoders.framework.core

import com.example.architectcoders.framework.wizard.network.HogwartsService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create

object HogwartsClient {
    private val contentType = "application/json".toMediaType()

    val instance = Retrofit.Builder()
        .baseUrl("https://hp-api.onrender.com/api/")
        .addConverterFactory(Json.asConverterFactory(contentType))
        .build()
        .create<HogwartsService>()
}