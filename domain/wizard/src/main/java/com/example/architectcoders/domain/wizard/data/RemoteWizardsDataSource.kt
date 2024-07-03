package com.example.architectcoders.domain.wizard.data

import com.example.architectcoders.domain.wizard.entities.WizardModel


interface RemoteWizardsDataSource {
    suspend fun fetchWizardsSortedByHouse(house: String): List<WizardModel>
    suspend fun getWizardById(id: String): WizardModel
}