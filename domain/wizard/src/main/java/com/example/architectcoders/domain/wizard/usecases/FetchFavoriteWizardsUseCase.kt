package com.example.architectcoders.domain.wizard.usecases

import com.example.architectcoders.domain.wizard.data.HogwartsRepository
import javax.inject.Inject

class FetchFavoriteWizardsUseCase @Inject constructor(private val hogwartsRepository: HogwartsRepository) {
    operator fun invoke() = hogwartsRepository.fetchFavoriteWizards()
}