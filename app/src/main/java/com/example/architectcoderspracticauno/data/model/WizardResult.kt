package com.example.architectcoderspracticauno.data.model

import com.example.architectcoderspracticauno.ui.model.WandModel
import com.example.architectcoderspracticauno.ui.model.WizardModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WizardResult(
    val actor: String,
    val alive: Boolean,
    @SerialName("alternate_actors") val alternateActors: List<String>,
    @SerialName("alternate_names") val alternateNames: List<String>,
    val ancestry: String,
    val dateOfBirth: String?,
    val yearOfBirth: Int?,
    val eyeColour: String,
    val gender: String,
    val hairColour: String,
    val hogwartsStaff: Boolean,
    val hogwartsStudent: Boolean,
    val house: String,
    val id: String,
    val image: String,
    val name: String,
    val patronus: String,
    val species: String,
    val wand: WandResult,
    val wizard: Boolean
)

@Serializable
data class WandResult(
    val core: String,
    val length: Double?,
    val wood: String
)

fun WizardResult.toWizardModel(): WizardModel {
    return WizardModel(
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
        )
    )
}