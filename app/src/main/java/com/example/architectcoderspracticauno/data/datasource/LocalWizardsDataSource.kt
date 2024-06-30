package com.example.architectcoderspracticauno.data.datasource

import com.example.architectcoderspracticauno.ui.model.WizardModel
import kotlinx.coroutines.flow.Flow

interface LocalWizardsDataSource {
       fun fetchWizardsByHouse(house: String): Flow<List<WizardModel>>
       fun fetchFavoriteWizards(): Flow<List<WizardModel>>
       fun findWizardById(id: String): Flow<WizardModel?>
       suspend fun saveWizards(wizards: List<WizardModel>)
       suspend fun updateWizard(wizard: WizardModel)
}