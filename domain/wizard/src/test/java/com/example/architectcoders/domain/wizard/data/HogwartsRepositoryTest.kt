package com.example.architectcoders.domain.wizard.data

import com.example.architectcoders.domain.wizard.entities.WizardModel
import com.example.architectcoders.domain.wizard.sampleWizard
import com.example.architectcoders.domain.wizard.sampleWizards
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.argThat
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class HogwartsRepositoryTest{
    @Mock lateinit var localDataSource: LocalWizardsDataSource
    @Mock lateinit var remoteDataSource: RemoteWizardsDataSource
    private lateinit var repository: HogwartsRepository

    @Before
    fun setUp() {
        repository = HogwartsRepository(remoteDataSource, localDataSource)
    }

    @Test
    fun `Wizards (sorted by house) are taken from local data source if avalaible`() = runBlocking {
        val localWizards = sampleWizards("1", "2", "3")
        whenever(localDataSource.fetchWizardsByHouse("Gryffindor")).thenReturn(flowOf(localWizards))

        val result = repository.fetchWizardsByHouse("Gryffindor")
        assertEquals(localWizards, result.first())
    }

    @Test
    fun `Wizards (sorted by house) are saved to local data source when it is empty`() = runBlocking {
        val localWizards = emptyList<WizardModel>()
        val remoteWizards = sampleWizards("33", "44")
        whenever(localDataSource.fetchWizardsByHouse("Gryffindor")).thenReturn(flowOf(localWizards))
        whenever(remoteDataSource.fetchWizardsSortedByHouse("Gryffindor")).thenReturn(remoteWizards)

        repository.fetchWizardsByHouse("Gryffindor").first()
        verify(localDataSource).saveWizards(remoteWizards)
    }

    @Test
    fun `Favorites wizards ara loaded from local data source`() = runBlocking {
        val localFavoriteWizards = sampleWizards("11", "22", "33")
        whenever(localDataSource.fetchFavoriteWizards()).thenReturn(flowOf(localFavoriteWizards))

        val result = repository.fetchFavoriteWizards()
        assertEquals(localFavoriteWizards, result.first())
    }

    @Test
    fun `Switching favorite marks as favorite an unfavorite wizard`() = runBlocking {
        val wizard = sampleWizard("1").copy(isFavorite = false)
        repository.toggleFavourite(wizard)
        verify(localDataSource).updateWizard(argThat { isFavorite })
    }
}