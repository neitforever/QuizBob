package com.example.spongebobgame.quizz_game

import QuizGame
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.spongebobgame.ui.theme.QuizTheme

@Composable
fun QuizGameActivity(navController: NavController) {
    QuizTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            QuizGame(navController = navController)
        }
    }
}