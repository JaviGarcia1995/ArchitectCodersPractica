package com.example.architectcoderspracticauno.ui.model

import com.example.architectcoderspracticauno.data.database.WandEntity
import com.example.architectcoderspracticauno.data.database.WizardEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WizardModel(
    val actor: String,
    val house: String,
    val id: String,
    val image: String,
    val name: String,
    val patronus: String,
    val wand: WandModel,
    val isFavorite: Boolean
)

@Serializable
data class WandModel(
    val core: String,
    val length: Double?,
    val wood: String
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