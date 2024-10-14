package org.kmp.kmp_ktor

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import kotlinktor.composeapp.generated.resources.Res
import kotlinktor.composeapp.generated.resources.compose_multiplatform
import org.kmp.kmp_ktor.screens.detail.DetailScreen
import org.kmp.kmp_ktor.screens.home.HomeScreen

enum class Screen {
    Home, Detail
}

@Composable
@Preview
fun App() {
    MaterialTheme {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            var currentScreen by remember { mutableStateOf(Screen.Home) }
            var selectedId by remember { mutableStateOf("") }
            when (currentScreen) {
                Screen.Home -> {
                    HomeScreen {
                        currentScreen = Screen.Detail
                        selectedId = it.idMeal
                    }
                }

                Screen.Detail -> {
                    DetailScreen(
//                        id = selectedId,
//                        navigateBack = {
//                            currentScreen = Screen.Home
//                            selectedId = ""
//                        }
                    )
                }

            }
        }
    }
}