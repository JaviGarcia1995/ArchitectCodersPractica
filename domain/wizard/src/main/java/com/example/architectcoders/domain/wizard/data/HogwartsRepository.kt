package com.example.architectcoders.domain.wizard.data

import com.example.architectcoders.domain.wizard.entities.WizardModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject


class HogwartsRepository @Inject constructor(
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