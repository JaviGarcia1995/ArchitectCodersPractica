package com.example.architectcoders.framework.wizard.network

import retrofit2.http.GET
import retrofit2.http.Path

interface HogwartsService {
    @GET("characters/house/{house}")
    suspend fun getWizardsSortedByHouse(@Path("house") house: String): List<WizardResult>

    @GET("character/{id}")
    suspend fun getWizardById(@Path("id") id: String): List<WizardResult>
}