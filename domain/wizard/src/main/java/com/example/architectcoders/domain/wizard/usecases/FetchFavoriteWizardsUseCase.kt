package com.example.architectcoders.domain.wizard.usecases

import com.example.architectcoders.domain.wizard.data.HogwartsRepository

class FetchFavoriteWizardsUseCase(private val hogwartsRepository: HogwartsRepository) {
    operator fun invoke() = hogwartsRepository.fetchFavoriteWizards()
}