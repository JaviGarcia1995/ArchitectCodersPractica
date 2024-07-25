package com.example.architectcoders.domain.wizard.usecases

import com.example.architectcoders.domain.wizard.sampleWizards
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.*
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class FetchFavoriteWizardsUseCaseTest {
    @Test
    fun `Invoke calls repository`() {
        val wizardFlow = flowOf(sampleWizards("1", "2"))
        val favoriteUseCase = FetchFavoriteWizardsUseCase( mock {
            on { fetchFavoriteWizards() } doReturn wizardFlow
        })

        val result = favoriteUseCase()
        assertEquals(wizardFlow, result)
    }
}