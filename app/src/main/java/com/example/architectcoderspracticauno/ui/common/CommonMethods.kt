package com.example.architectcoderspracticauno.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.architectcoderspracticauno.R
import com.example.architectcoderspracticauno.data.model.Wizard
import com.example.architectcoderspracticauno.ui.theme.BackgroundBars
import com.example.architectcoderspracticauno.ui.theme.GryffindorRed
import com.example.architectcoderspracticauno.ui.theme.HufflepuffDarkYellow
import com.example.architectcoderspracticauno.ui.theme.RavenclawBlue
import com.example.architectcoderspracticauno.ui.theme.SlytherinGreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController


fun getColorByHouse(house: String): Color {
    return when (house){
        "Gryffindor" -> {
            GryffindorRed
        }
        "Slytherin" -> {
            SlytherinGreen
        }
        "Ravenclaw" -> {
            RavenclawBlue
        }
        "Hufflepuff" -> {
            HufflepuffDarkYellow
        }
        else -> {
            GryffindorRed
        }
    }
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
            .clip(RoundedCornerShape(16.dp))
            .border(
                BorderStroke(3.dp, getColorByHouse(wizard.house)),
                RoundedCornerShape(16.dp)
            )
    )
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
            .clip(RoundedCornerShape(16.dp))
            .border(
                BorderStroke(3.dp, getColorByHouse(wizard.house)),
                RoundedCornerShape(16.dp)
            )
    )
}

@Composable
fun ChangeStatusBarColor() {
    // Change StatusBar Color with Accompanist library
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(BackgroundBars)

    SideEffect {
        systemUiController.setStatusBarColor(
            color = BackgroundBars,
        )
    }
}

fun String.capitalize(): String {
    return this.replaceFirstChar(Char::titlecase)
}
