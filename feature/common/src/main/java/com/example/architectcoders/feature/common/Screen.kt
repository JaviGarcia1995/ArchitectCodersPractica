package com.example.architectcoders.feature.common

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.architectcoders.feature.common.theme.ArchitectCodersPracticaUnoTheme

@Composable
fun Screen(content: @Composable () -> Unit) {
    ArchitectCodersPracticaUnoTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
            content = content
        )
    }
}