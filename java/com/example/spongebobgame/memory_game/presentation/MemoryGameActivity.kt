package com.example.spongebobgame.memory_game.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.spongebobgame.memory_game.presentation.memory_screen.MemoryScreen
import com.example.spongebobgame.memory_game.presentation.memory_screen.MemoryViewModel
import com.example.spongebobgame.ui.theme.SpongeBobTheme


@Composable
fun MemoryGameActivity(navController: NavController) {
    val viewModel: MemoryViewModel = viewModel()
    SpongeBobTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            MemoryScreen(viewModel = viewModel, navController = navController)
        }
    }
}
