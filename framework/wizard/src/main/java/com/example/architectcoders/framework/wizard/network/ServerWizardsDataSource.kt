package com.example.architectcoders.framework.wizard.network

import com.example.architectcoders.domain.wizard.data.RemoteWizardsDataSource
import com.example.architectcoders.domain.wizard.entities.WandModel
import com.example.architectcoders.domain.wizard.entities.WizardModel

class ServerWizardsDataSource(private val hogwartsService: HogwartsService):
    RemoteWizardsDataSource {
    override suspend fun fetchWizardsSortedByHouse(house: String): List<WizardModel> =
        hogwartsService
            .getWizardsSortedByHouse(house)
            .map { it.toWizardModel() }

    override suspend fun getWizardById(id: String): WizardModel =
        hogwartsService
            .getWizardById(id)
            .first()
            .toWizardModel()
}

private fun WizardResult.toWizardModel(): WizardModel =
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
        isFavorite = false
    )