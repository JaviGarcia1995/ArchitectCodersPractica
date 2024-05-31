package com.example.architectcoderspracticauno.data.repository

import com.example.architectcoderspracticauno.data.dataSources.RemoteWizardsDataSource

class HogwartsRepository(
    private val remoteWizardsDataSource: RemoteWizardsDataSource
){
    suspend fun getWizardsSortedByHouse(house: String) =
        remoteWizardsDataSource.getWizardsSortedByHouse(house)

    suspend fun getWizardById(id: String) =
        remoteWizardsDataSource.getWizardById(id)
}