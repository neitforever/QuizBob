package com.example.spongebobgame.quizz_game

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.spongebobgame.R
import com.example.spongebobgame.quizz_game.data.Question
import com.example.spongebobgame.ui.theme.Colors

@Composable
fun MainContent(
    currentQuestion: Question,
    selectedAnswer: String,
    onAnswerSelected: (String) -> Unit,
    onAnswerConfirmed: (Boolean) -> Unit,
    navController: NavController,
    onSnackbarDismiss: () -> Unit
) {
    var warningVisible by remember { mutableStateOf(false) }
    val orientation = LocalConfiguration.current.orientation
    val rowModifier = if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
        Modifier.fillMaxWidth(0.6f)
    } else {
        Modifier.fillMaxWidth()
    }
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = currentQuestion.statement,
            modifier = Modifier
                .padding(8.dp)
                .background(Colors.purple, shape = RoundedCornerShape(10.dp))
                .padding(16.dp),
            color = Colors.Yellow,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        )
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            currentQuestion.propositions.forEach { proposition ->
                AnswerButton(
                    text = proposition,
                    onClick = {
                        onAnswerSelected(proposition)
                        warningVisible = false
                    },
                    modifier = Modifier.padding(vertical = 4.dp),
                    textColor = if (selectedAnswer == proposition) Color.Green else Colors.swamp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = rowModifier
            ) {
                Button(
                    onClick = {
                        navController.navigate("main_screen")
                    },
                    colors = ButtonDefaults.buttonColors(
                        Colors.purple
                    ),
                    modifier = Modifier.height(32.dp)
                ) {
                    Text(
                        color = Colors.Yellow,
                        text = stringResource(id = R.string.close_quiz)
                    )
                }

                Button(
                    onClick = {
                        if (selectedAnswer.isBlank()) {
                            warningVisible = true
                        } else {
                            val isCorrect = selectedAnswer == currentQuestion.response
                            onAnswerConfirmed(isCorrect)
                            warningVisible = false
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        Colors.purple
                    ),
                    modifier = Modifier.height(32.dp)
                ) {
                    Text(
                        color = Colors.Yellow,
                        text = stringResource(id = R.string.accept_quiz)
                    )
                }
            }

            AnimatedVisibility(visible = warningVisible) {
                Text(
                    color = Color.Red,
                    modifier = Modifier
                        .padding(2.dp)
                        .background(Colors.answerbgc, shape = RoundedCornerShape(4.dp))
                        .padding(2.dp),
                    text = stringResource(id = R.string.unclicked_warning_quiz),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                )
            }
        }
    }
}