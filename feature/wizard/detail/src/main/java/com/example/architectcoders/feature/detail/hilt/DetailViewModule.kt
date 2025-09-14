package com.example.architectcoders.feature.detail.hilt

import androidx.lifecycle.SavedStateHandle
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class DetailViewModule {
    @Provides
    @ViewModelScoped
    @WizardId
    fun provideWizardId(savedStateHandle: SavedStateHandle): String =
        savedStateHandle.get<String>("wizardId") ?: throw IllegalArgumentException("wizardId is required")

}