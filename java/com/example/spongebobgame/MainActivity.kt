package com.example.spongebobgame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.spongebobgame.main_screen.MainScreen
import com.example.spongebobgame.memory_game.presentation.MemoryGameActivity
import com.example.spongebobgame.quizz_game.QuizGameActivity
import com.example.spongebobgame.ui.theme.SpongeBobTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            val navController = rememberNavController()

            SpongeBobTheme {
                NavHost(
                    navController = navController,
                    startDestination = "main_screen"
                ) {
                    composable("main_screen") {
                        MainScreen(navController)
                    }
                    composable("memory_game") {
                        MemoryGameActivity(navController)
                    }
                    composable("quiz_game") {
                        QuizGameActivity(navController)
                    }
                }
            }
        }
    }
}

