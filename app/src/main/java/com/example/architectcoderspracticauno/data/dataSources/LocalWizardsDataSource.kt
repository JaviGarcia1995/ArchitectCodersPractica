package com.example.architectcoderspracticauno.data.dataSources

import com.example.architectcoderspracticauno.data.database.WizardsDao
import com.example.architectcoderspracticauno.data.database.toWizardModel
import com.example.architectcoderspracticauno.ui.model.WizardModel
import com.example.architectcoderspracticauno.ui.model.toWizardEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalWizardsDataSource(private val wizardsDao: WizardsDao) {
       fun fetchWizardsByHouse(house: String): Flow<List<WizardModel>> =
              wizardsDao.getWizardsByHouse(house).map { wizardEntities ->
                     wizardEntities.map { it.toWizardModel() }
              }

       fun fetchFavoriteWizards(): Flow<List<WizardModel>> =
              wizardsDao.getFavoriteWizards().map { wizardEntities ->
                     wizardEntities.map { it.toWizardModel() }
              }

       fun findWizardById(id: String) =
              wizardsDao
                     .getWizardById(id)
                     .map { it?.toWizardModel() }

       suspend fun saveWizards(wizards: List<WizardModel>) {
              val wizardEntities = wizards.map {it.toWizardEntity()}
              wizardsDao.saveWizards(wizardEntities)
       }

       suspend fun updateWizard(wizard: WizardModel) {
              val wizardEntity = wizard.toWizardEntity()
              wizardsDao.updateWizard(wizardEntity.isFavorite, wizardEntity.id)
       }
}