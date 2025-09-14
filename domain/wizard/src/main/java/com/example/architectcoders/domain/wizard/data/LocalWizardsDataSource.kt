package com.example.architectcoders.domain.wizard.data

import com.example.architectcoders.domain.wizard.entities.WizardModel
import kotlinx.coroutines.flow.Flow

interface LocalWizardsDataSource {
       fun fetchWizardsByHouse(house: String): Flow<List<WizardModel>>
       fun fetchFavoriteWizards(): Flow<List<WizardModel>>
       fun findWizardById(id: String): Flow<WizardModel?>
       suspend fun saveWizards(wizards: List<WizardModel>)
       suspend fun updateWizard(wizard: WizardModel)
}