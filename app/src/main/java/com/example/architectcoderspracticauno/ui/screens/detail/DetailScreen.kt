package com.example.architectcoderspracticauno.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.architectcoderspracticauno.R
import com.example.architectcoderspracticauno.ui.common.ChangeStatusBarColor
import com.example.architectcoderspracticauno.ui.common.LoadImage
import com.example.architectcoderspracticauno.ui.common.Screen
import com.example.architectcoderspracticauno.ui.common.capitalize
import com.example.architectcoderspracticauno.ui.common.getColorByHouse
import com.example.architectcoderspracticauno.ui.model.WizardModel
import com.example.architectcoderspracticauno.ui.theme.BackgroundApp
import com.example.architectcoderspracticauno.ui.theme.BackgroundBars
import com.example.architectcoderspracticauno.ui.theme.SelectedBarItem

@Composable
fun DetailScreen(
    vm: DetailViewModel,
    onBack: () -> Unit
) {
    val state by vm.state.collectAsState()

    Screen {
        ChangeStatusBarColor()
        Scaffold(
            topBar = {
                DetailTopBar(
                    title = state.wizard?.name ?: "",
                    onBack = onBack
                )
            },
            floatingActionButton = {
                state.wizard?.let { wizard ->
                    DetailFloatingButton(
                        onFavouriteClick = { vm.toggleFavourite() },
                        wizard = wizard,
                        isFavourite = state.isFavourite
                    )
                }
            }
        ){ padding ->
            state.wizard?.let { wizard ->
                DetailWizard(
                    modifier = Modifier.padding(padding),
                    wizard = wizard
                )
            }
        }
    }
}

@Composable
private fun DetailWizard(
    modifier: Modifier,
    wizard: WizardModel
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundApp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
                LoadImage(wizard)

                Spacer(modifier = Modifier.width(16.dp))

            Column{
                Text(
                    text = wizard.name,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "House: ${wizard.house}",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Patronus: ${wizard.patronus.capitalize()}",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Actor: ${wizard.actor}",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun DetailTopBar(
    title: String,
    onBack: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = Color.White
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = BackgroundBars),
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.back),
                    tint = Color.White
                )
            }
        }
    )
}

@Composable
private fun DetailFloatingButton(
    onFavouriteClick: () -> Unit,
    wizard: WizardModel,
    isFavourite: Boolean
) {
    FloatingActionButton(
        onClick =  onFavouriteClick,
        shape = CircleShape,
        modifier = Modifier.padding(16.dp),
        containerColor = SelectedBarItem,
        contentColor = getColorByHouse(wizard.house)
    ){
        Icon(
            imageVector = if (isFavourite) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
            contentDescription = if (isFavourite) "Add to favourite" else "Remove from favourite"
        )
    }
}