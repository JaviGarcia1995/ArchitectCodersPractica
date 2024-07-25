package com.example.architectcoders.domain.wizard.usecases

import com.example.architectcoders.domain.wizard.sampleWizard
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.*
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class FindWizardByIdUseCaseTest{
    @Test
    fun `Invoke calls repository`() {
        val wizardFlow = flowOf(sampleWizard("5"))
        val findWizardByIdUseCase = FindWizardByIdUseCase(mock {
            on { findWizardById("5") } doReturn wizardFlow
        })

        val result = findWizardByIdUseCase("5")
        assertEquals(wizardFlow, result)
    }
}