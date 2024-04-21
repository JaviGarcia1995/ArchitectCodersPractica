package com.example.architectcoderspracticauno.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Wizard(
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
    val wand: Wand,
    val wizard: Boolean
)

@Serializable
data class Wand(
    val core: String,
    val length: Double?,
    val wood: String
)