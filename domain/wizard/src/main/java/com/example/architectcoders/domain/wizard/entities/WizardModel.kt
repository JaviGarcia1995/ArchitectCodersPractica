package com.example.architectcoders.domain.wizard.entities

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

data class WandModel(
    val core: String,
    val length: Double?,
    val wood: String
)