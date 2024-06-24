package com.example.architectcoderspracticauno.data.dataSources

import com.example.architectcoderspracticauno.data.model.toWizardModel
import com.example.architectcoderspracticauno.data.repository.HogwartsClient
import com.example.architectcoderspracticauno.ui.model.WizardModel

class RemoteWizardsDataSource {
    suspend fun fetchWizardsSortedByHouse(house: String): List<WizardModel> =
        HogwartsClient
            .instance
            .getWizardsSortedByHouse(house)
            .map { it.toWizardModel() }

    suspend fun getWizardById(id: String): WizardModel =
        HogwartsClient
            .instance
            .getWizardById(id)
            .first()
            .toWizardModel()
}