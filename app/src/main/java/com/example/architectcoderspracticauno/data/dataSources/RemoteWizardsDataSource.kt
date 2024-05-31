package com.example.architectcoderspracticauno.data.dataSources

import com.example.architectcoderspracticauno.data.model.WizardResult
import com.example.architectcoderspracticauno.data.repository.HogwartsClient

class RemoteWizardsDataSource {
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