package com.example.architectcoders.domain.wizard

import com.example.architectcoders.domain.wizard.entities.WandModel
import com.example.architectcoders.domain.wizard.entities.WizardModel

fun sampleWizard(id: String) = WizardModel(
    id = id,
    name = "Wizard $id",
    actor = "Actor $id",
    house = "House $id",
    image = "https://example.com/image$id.jpg",
    patronus = "Patronus $id",
    wand = WandModel(
        wood = "Wood $id",
        core = "Core $id",
        length = 123.45
    ),
    isFavorite = false
)

fun sampleWizards(vararg ids: String) = ids.map(::sampleWizard)