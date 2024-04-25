package com.example.architectcoderspracticauno.data.repository

import com.example.architectcoderspracticauno.data.model.Wizard
import retrofit2.http.GET
import retrofit2.http.Path

interface HogwartsService {
    @GET("characters/house/{house}")
    suspend fun getWizardsSortedByHouse(@Path("house") house: String): List<Wizard>

    @GET("character/{id}")
    suspend fun getWizardById(@Path("id") id: String): List<Wizard>
}