package com.example.architectcoders.data

import com.example.architectcoders.domain.wizard.data.HogwartsRepository
import com.example.architectcoders.domain.wizard.data.LocalWizardsDataSource
import com.example.architectcoders.domain.wizard.data.RemoteWizardsDataSource
import com.example.architectcoders.domain.wizard.entities.WizardModel
import com.example.architectcoders.domain.wizard.sampleWizards
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

fun buildHogwartsRepositoryWith(
    localData: List<WizardModel> = emptyList(),
    remoteData: List<WizardModel> = emptyList()
):HogwartsRepository{
    val localDataSource = FakeLocalDataSource().apply { inMemoryData.value = localData }
    val remoteDataSource = FakeRemoteDataSource().apply { wizards = remoteData }
    return HogwartsRepository(remoteDataSource, localDataSource)
}

class FakeLocalDataSource: LocalWizardsDataSource{
    val inMemoryData = MutableStateFlow<List<WizardModel>>(emptyList())

    override fun fetchWizardsByHouse(house: String): Flow<List<WizardModel>> =
        inMemoryData.map { it.filter { wizard -> wizard.house.equals(house, ignoreCase = true) } }


    override fun fetchFavoriteWizards(): Flow<List<WizardModel>>  =
        inMemoryData.map { it.filter { wizard -> wizard.isFavorite } }

    override fun findWizardById(id: String): Flow<WizardModel?> =
        inMemoryData.map { it.firstOrNull { wizard -> wizard.id == id } }

    override suspend fun saveWizards(wizards: List<WizardModel>) {
        inMemoryData.value = wizards
    }

    override suspend fun updateWizard(wizard: WizardModel) {
        inMemoryData.value = inMemoryData.value.map { existingWizard ->
            if (existingWizard.id == wizard.id) {
                existingWizard.copy(isFavorite = !wizard.isFavorite)
            } else {
                existingWizard
            }
        }
    }
}

class FakeRemoteDataSource(): RemoteWizardsDataSource {
    var wizards = sampleWizards("1", "2", "3")

    override suspend fun fetchWizardsSortedByHouse(house: String): List<WizardModel> = wizards.sortedBy { it.house }

    override suspend fun getWizardById(id: String): WizardModel = wizards.first { it.id == id }
}