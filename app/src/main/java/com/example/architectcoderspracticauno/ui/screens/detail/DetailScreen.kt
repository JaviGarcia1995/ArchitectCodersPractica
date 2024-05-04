package com.example.architectcoderspracticauno.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.architectcoderspracticauno.R
import com.example.architectcoderspracticauno.ui.common.ChangeStatusBarColor
import com.example.architectcoderspracticauno.ui.common.LoadImageFromInternet
import com.example.architectcoderspracticauno.ui.common.LoadImageFromLocal
import com.example.architectcoderspracticauno.ui.common.Screen
import com.example.architectcoderspracticauno.ui.common.capitalize
import com.example.architectcoderspracticauno.ui.theme.BackgroundApp
import com.example.architectcoderspracticauno.ui.theme.BackgroundBars

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    wizardId: String,
    onBack: () -> Unit
) {
    val vm: DetailViewModel = viewModel()
    val state = vm.state

    LaunchedEffect(Unit) {
        vm.loadWizardProfile(wizardId = wizardId)
    }

    Screen {

        ChangeStatusBarColor()

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = state.wizard?.name ?: "",
                            color = Color.White
                        )
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = BackgroundBars),
                    navigationIcon = {
                        IconButton(onClick = onBack ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = stringResource(id = R.string.back),
                                tint = Color.White
                            )
                        }
                    }
                )
            }
        ){ padding ->
            if (state.loading){
                Box (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .background(BackgroundApp),
                    contentAlignment = Alignment.Center
                ){
                    CircularProgressIndicator()
                }
            }

            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .background(BackgroundApp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Row(
                    modifier = Modifier
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    state.wizard?.let {
                        if (state.wizard.image!="")
                            LoadImageFromInternet(it)
                        else
                            LoadImageFromLocal(state.wizard)

                        Spacer(modifier = Modifier.width(16.dp))

                        Column (
                        ){
                            Text(
                                text = state.wizard.name,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold,
                                fontSize = 22.sp,
                                color = Color.White
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "House: ${state.wizard.house}",
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold,
                                fontSize = 22.sp,
                                color = Color.White
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "Patronus: ${state.wizard.patronus.capitalize()}",
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold,
                                fontSize = 22.sp,
                                color = Color.White
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "Actor: ${state.wizard.actor}",
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold,
                                fontSize = 22.sp,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}