package com.example.architectcoders.domain.wizard.usecases

import com.example.architectcoders.domain.wizard.data.HogwartsRepository
import com.example.architectcoders.domain.wizard.sampleWizard
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class ToggleFavoriteUseCaseTest{
    @Test
    fun `Invoke calls repository`() = runBlocking {
        val wizard = sampleWizard("6")
        val repository = mock<HogwartsRepository>()
        val toggleFavoriteUseCase = ToggleFavoriteUseCase(repository)

        toggleFavoriteUseCase(wizard)
        verify(repository).toggleFavourite(wizard)
    }
}