package com.example.architectcoderspracticauno.data.datasource

import com.example.architectcoderspracticauno.ui.model.WizardModel

interface RemoteWizardsDataSource {
    suspend fun fetchWizardsSortedByHouse(house: String): List<WizardModel>
    suspend fun getWizardById(id: String): WizardModel
}