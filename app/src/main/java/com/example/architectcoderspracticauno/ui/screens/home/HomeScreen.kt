package com.example.architectcoderspracticauno.ui.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomAppBar
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.architectcoderspracticauno.R
import com.example.architectcoderspracticauno.data.model.Wizard
import com.example.architectcoderspracticauno.ui.common.Screen
import com.example.architectcoderspracticauno.ui.theme.BackgroundApp
import com.example.architectcoderspracticauno.ui.theme.BackgroundBars
import com.example.architectcoderspracticauno.ui.theme.GryffindorRed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onWizardClicked: (Wizard) -> Unit,
    vm: HomeViewModel = viewModel()
) {
    vm.onUiReady("gryffindor")

    Screen {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Harry Potter API", color = Color.White) },

                    scrollBehavior = scrollBehavior,
                    colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = BackgroundBars)
                )
            },
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            contentWindowInsets = WindowInsets.safeDrawing,
            bottomBar = ({ HomeBottomBar() })
        ) {padding ->
            val state = vm.state

            if (state.loading){
                Box (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ){
                    CircularProgressIndicator()
                }
            }

            LazyVerticalGrid(
                modifier = Modifier.background(BackgroundApp),
                columns = GridCells.Adaptive(120.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                contentPadding = padding,
            ) {
                items(state.wizards, key = { it.id }){wizard ->
                    WizardItem(
                        wizard = wizard,
                        onWizardClicked = { onWizardClicked(wizard) }
                    )
                }
            }
        }
    }
}

@Composable
fun HomeBottomBar() {
    BottomAppBar(
        containerColor = BackgroundBars
    ) {
        Row (
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ){
            IconButton(
                onClick = { /* Handle navigation icon click */ }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_gryffindor),
                    contentDescription = "Gryffindor",
                    tint = Color.Unspecified
                )
            }
            IconButton(
                onClick = { /* Handle navigation icon click */ }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_slytherin),
                    contentDescription = "Slytherin",
                    tint = Color.Unspecified
                )
            }

            IconButton(
                onClick = { /* Handle navigation icon click */ }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_ravenclaw),
                    contentDescription = "Ravenclaw",
                    tint = Color.Unspecified
                )
            }

            IconButton(
                onClick = { /* Handle navigation icon click */ }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_hufflepuff),
                    contentDescription = "Hufflepuff",
                    tint = Color.Unspecified
                )
            }
        }

    }
}

@Composable
fun WizardItem(wizard: Wizard, onWizardClicked: () -> Unit) {
    Column(
        modifier = Modifier.clickable { onWizardClicked() }
    ) {
        if (wizard.image != "")
            LoadImageFromInternet(wizard)
        else
            LoadImageFromLocal(wizard)

        Text(
            text = wizard.name,
            color = Color.White,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun LoadImageFromInternet(wizard: Wizard) {
    AsyncImage(
        model = wizard.image,
        contentDescription = wizard.name,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(150.dp)
            .padding(5.dp)
            .clip(CircleShape)
            .border(
                BorderStroke(3.dp, GryffindorRed),
                CircleShape
            )
    )
}

@Composable
fun LoadImageFromLocal(wizard: Wizard) {
    Image(
        painter = painterResource(id = R.drawable.im_placeholder),
        contentDescription = wizard.name,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(150.dp)
            .padding(5.dp)
            .clip(CircleShape)
            .border(
                BorderStroke(3.dp, GryffindorRed),
                CircleShape
            )
    )
}