package com.example.architectcoders.framework.wizard.database

import com.example.architectcoders.domain.wizard.data.LocalWizardsDataSource
import com.example.architectcoders.domain.wizard.entities.WandModel
import com.example.architectcoders.domain.wizard.entities.WizardModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomWizardsDataSource @Inject constructor(private val wizardsDao: WizardsDao) :
    LocalWizardsDataSource {
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