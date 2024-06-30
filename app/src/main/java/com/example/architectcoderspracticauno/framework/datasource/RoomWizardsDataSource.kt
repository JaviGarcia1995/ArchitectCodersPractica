package com.example.architectcoderspracticauno.framework.datasource

import com.example.architectcoderspracticauno.data.datasource.LocalWizardsDataSource
import com.example.architectcoderspracticauno.framework.database.WandEntity
import com.example.architectcoderspracticauno.framework.database.WizardEntity
import com.example.architectcoderspracticauno.framework.database.WizardsDao
import com.example.architectcoderspracticauno.ui.model.WandModel
import com.example.architectcoderspracticauno.ui.model.WizardModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomWizardsDataSource(private val wizardsDao: WizardsDao) : LocalWizardsDataSource {
       override fun fetchWizardsByHouse(house: String): Flow<List<WizardModel>> =
              wizardsDao.getWizardsByHouse(house).map { wizardEntities ->
                     wizardEntities.map { it.toWizardModel() }
              }

       override fun fetchFavoriteWizards(): Flow<List<WizardModel>> =
              wizardsDao.getFavoriteWizards().map { wizardEntities ->
                     wizardEntities.map { it.toWizardModel() }
              }

       override fun findWizardById(id: String) =
              wizardsDao
                     .getWizardById(id)
                     .map { it?.toWizardModel() }

       override suspend fun saveWizards(wizards: List<WizardModel>) {
              val wizardEntities = wizards.map {it.toWizardEntity()}
              wizardsDao.saveWizards(wizardEntities)
       }

       override suspend fun updateWizard(wizard: WizardModel) {
              val wizardEntity = wizard.toWizardEntity()
              wizardsDao.updateWizard(wizardEntity.isFavorite, wizardEntity.id)
       }
}

private fun WizardEntity.toWizardModel(): WizardModel =
       WizardModel(
              id = id,
              actor = actor,
              house = house,
              image = image,
              name = name,
              patronus = patronus,
              wand = WandModel(
                     core = wand.core,
                     length = wand.length,
                     wood = wand.wood
              ),
              isFavorite = isFavorite
       )

fun WizardModel.toWizardEntity(): WizardEntity =
       WizardEntity(
              id = id,
              actor = actor,
              house = house,
              image = image,
              name = name,
              patronus = patronus,
              wand = WandEntity(
                     core = wand.core,
                     length = wand.length,
                     wood = wand.wood
              ),
              isFavorite = isFavorite
       )