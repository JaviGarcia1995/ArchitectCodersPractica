package com.example.architectcoderspracticauno.data.repository

import com.example.architectcoderspracticauno.data.dataSources.LocalWizardsDataSource
import com.example.architectcoderspracticauno.data.dataSources.RemoteWizardsDataSource
import com.example.architectcoderspracticauno.ui.model.WizardModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

class HogwartsRepository(
    private val remoteWizardsDataSource: RemoteWizardsDataSource,
    private val localWizardsDataSource: LocalWizardsDataSource
){
    fun fetchWizardsByHouse(house: String): Flow<List<WizardModel>> =
        localWizardsDataSource.fetchWizardsByHouse(house).transform { localWizards ->
            val wizards = localWizards.takeIf { it.isNotEmpty() }
                ?: remoteWizardsDataSource.fetchWizardsSortedByHouse(house).also {
                    localWizardsDataSource.saveWizards(it)
                }
            emit(wizards)
        }

    fun fetchFavoriteWizards(): Flow<List<WizardModel>> =
        localWizardsDataSource.fetchFavoriteWizards().transform { localWizards ->
            emit(localWizards)
        }

    fun findWizardById(id: String): Flow<WizardModel?> =
        localWizardsDataSource.findWizardById(id).transform { localWizard ->
            val wizard = localWizard ?: remoteWizardsDataSource.getWizardById(id).also {
                localWizardsDataSource.saveWizards(listOf(it))
            }
            emit(wizard)
        }

    suspend fun toggleFavourite(wizard: WizardModel) {
        val updatedWizard = wizard.copy(isFavorite = !wizard.isFavorite)
        localWizardsDataSource.updateWizard(updatedWizard)
    }
}