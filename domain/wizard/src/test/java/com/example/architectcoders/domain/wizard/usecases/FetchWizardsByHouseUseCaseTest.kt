package com.example.architectcoders.domain.wizard.usecases

import com.example.architectcoders.domain.wizard.sampleWizards
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.*
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class FetchWizardsByHouseUseCaseTest{
    @Test
    fun `Invoke calls repository`() {
        val wizardFlow = flowOf(sampleWizards("3", "4"))
        val fetchWizardsByHouseUseCase = FetchWizardsByHouseUseCase(mock {
            on { fetchWizardsByHouse("Gryffindor") } doReturn wizardFlow
        })

        val result = fetchWizardsByHouseUseCase("Gryffindor")
        assertEquals(wizardFlow, result)
    }
}