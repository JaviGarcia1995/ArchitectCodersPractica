package com.example.architectcoders.feature.detail

import com.example.architectcoders.feature.common.interfaces.Result
import com.example.architectcoders.feature.detail.DetailViewModel.UiState
import kotlinx.coroutines.flow.StateFlow

interface IDetailViewModel {
    val state: StateFlow<Result<UiState>>

    fun toggleFavourite()
}