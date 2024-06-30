package com.example.architectcoderspracticauno.framework.datasource

import com.example.architectcoderspracticauno.data.datasource.RemoteWizardsDataSource
import com.example.architectcoderspracticauno.data.model.WizardResult
import com.example.architectcoderspracticauno.framework.remote.HogwartsService
import com.example.architectcoderspracticauno.ui.model.WandModel
import com.example.architectcoderspracticauno.ui.model.WizardModel

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