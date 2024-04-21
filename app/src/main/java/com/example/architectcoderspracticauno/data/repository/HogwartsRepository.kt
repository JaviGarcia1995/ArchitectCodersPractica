package com.example.architectcoderspracticauno.data.repository

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

private val json = Json {
    ignoreUnknownKeys = true
}

object HogwartsRepository {
    val contentType = "application/json".toMediaType()

    val instance = Retrofit.Builder()
        .baseUrl("https://hp-api.onrender.com/api/")
        .addConverterFactory(json.asConverterFactory(contentType))
        .build()
}