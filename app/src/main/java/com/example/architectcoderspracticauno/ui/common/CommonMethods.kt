package com.example.architectcoderspracticauno.ui.common

import androidx.compose.ui.graphics.Color
import com.example.architectcoderspracticauno.ui.theme.GryffindorRed
import com.example.architectcoderspracticauno.ui.theme.HufflepuffYellow
import com.example.architectcoderspracticauno.ui.theme.RavenclawBlue
import com.example.architectcoderspracticauno.ui.theme.SlytherinGreen


fun getColorByHouse(house: String): Color {
    return when (house){
        "gryffindor" -> {
            GryffindorRed
        }
        "slytherin" -> {
            SlytherinGreen
        }
        "ravenclaw" -> {
            RavenclawBlue
        }
        "hufflepuff" -> {
            HufflepuffYellow
        }
        else -> {
            GryffindorRed
        }
    }
}