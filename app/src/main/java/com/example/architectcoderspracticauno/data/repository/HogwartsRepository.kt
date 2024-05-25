package com.example.architectcoderspracticauno.data.repository

import com.example.architectcoderspracticauno.data.model.WizardResult

class HogwartsRepository {
    suspend fun getWizardsSortedByHouse(house: String): List<WizardResult> =
        HogwartsClient
            .instance
            .getWizardsSortedByHouse(house)

    suspend fun getWizardById(id: String): WizardResult =
        HogwartsClient
            .instance
            .getWizardById(id)
            .first()
}