package com.example.architectcoderspracticauno.data.repository

import com.example.architectcoderspracticauno.data.model.Wizard

class HogwartsRepository {
    suspend fun getWizardsSortedByHouse(house: String): List<Wizard> =
        HogwartsClient
            .instance
            .getWizardsSortedByHouse(house)
}