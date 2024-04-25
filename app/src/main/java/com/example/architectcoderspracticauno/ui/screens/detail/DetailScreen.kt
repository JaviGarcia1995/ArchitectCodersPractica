package com.example.architectcoderspracticauno.ui.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.architectcoderspracticauno.R
import com.example.architectcoderspracticauno.ui.common.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    vm: DetailViewModel,
    onBack: () -> Unit
) {
    Screen {
        val state = vm.state
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = state.wizard?.name ?: "") },
                    navigationIcon = {
                        IconButton(onClick = onBack ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = stringResource(id = R.string.back)
                            )
                        }
                    }
                )
            }
        ){padding ->
            Column(
                modifier = Modifier.padding(padding)
            ){

            }
        }


    }
}