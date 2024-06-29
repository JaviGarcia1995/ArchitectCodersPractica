package com.example.architectcoderspracticauno.data.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.architectcoderspracticauno.ui.model.WandModel
import com.example.architectcoderspracticauno.ui.model.WizardModel

@Entity(tableName = "wizards")
data class WizardEntity(
    @PrimaryKey(autoGenerate = false) val id: String,
    val actor: String,
    val house: String,
    val image: String,
    val name: String,
    val patronus: String,
    @Embedded val wand: WandEntity,
    val isFavorite: Boolean
)

data class WandEntity(
    val core: String,
    val length: Double?,
    val wood: String
)

fun WizardEntity.toWizardModel(): WizardModel =
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