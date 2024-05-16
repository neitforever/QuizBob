import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.spongebobgame.R
import com.example.spongebobgame.quizz_game.MainContent
import com.example.spongebobgame.quizz_game.data.QuestionsDataProvider
import com.example.spongebobgame.ui.theme.Colors
import kotlinx.coroutines.delay
import kotlin.random.Random


@Composable
fun QuizGame(navController: NavController) {
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf(0) }
    var selectedAnswer by remember { mutableStateOf("") }
    var answeredQuestions by remember { mutableStateOf(0) }
    var snackbarVisible by remember { mutableStateOf(false) }

    var questions by rememberSaveable { mutableStateOf(QuestionsDataProvider.questions.shuffled(Random.Default).take(10)) }

    val (isQuizFinished, setIsQuizFinished) = remember { mutableStateOf(false) }

    val startTime = remember { mutableStateOf(0L) }
    val endTime = remember { mutableStateOf(0L) }
    val currentTime = remember { mutableStateOf(0L) }



    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    val backgroundDrawable = if (isLandscape) R.drawable.background_horizontal_quizz else R.drawable.background_vertical_quizz


    fun onAnswerSelected(proposition: String) {
        selectedAnswer = proposition
    }

    if (currentQuestionIndex == 0 && answeredQuestions == 0 && startTime.value == 0L) {
        startTime.value = System.currentTimeMillis()
    }

    LaunchedEffect(key1 = true) {
        while (true) {
            currentTime.value = System.currentTimeMillis()
            delay(1000)
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = backgroundDrawable),
            contentDescription = "Background Image",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            if (!isQuizFinished) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Результат: $score / ${questions.size}",
                        modifier = Modifier
                            .padding(vertical = 8.dp, horizontal = 8.dp)
                            .background(Colors.purple, shape = RoundedCornerShape(10.dp))
                            .padding(4.dp),
                        color = Colors.Yellow,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    )
                    Text(
                        text = "Время: ${(currentTime.value - startTime.value) / 1000} секунд",
                        modifier = Modifier
                            .padding(vertical = 8.dp, horizontal = 8.dp)
                            .background(Colors.purple, shape = RoundedCornerShape(10.dp))
                            .padding(4.dp),
                        color = Colors.Yellow,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    )
                }
                LinearProgressIndicator(
                    progress = answeredQuestions.toFloat() / questions.size,
                    color = Color.Green,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
                )
            }

            if (!isQuizFinished && currentQuestionIndex < questions.size) {
                val currentQuestion = questions[currentQuestionIndex]
                MainContent(
                    currentQuestion = currentQuestion,
                    selectedAnswer = selectedAnswer,
                    onAnswerSelected = ::onAnswerSelected,
                    onAnswerConfirmed = { isCorrect ->
                        if (selectedAnswer.isEmpty()) {
                            snackbarVisible = true
                        } else {
                            if (isCorrect) {
                                score++
                            }
                            currentQuestionIndex++
                            answeredQuestions++
                            selectedAnswer = ""
                            if (answeredQuestions == questions.size) {
                                setIsQuizFinished(true)
                            }
                        }
                    },
                    navController = navController,
                    onSnackbarDismiss = { snackbarVisible = false }
                )
            } else {
                if (endTime.value == 0L) {
                    endTime.value = System.currentTimeMillis()
                }

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    ) {
                        Text(
                            text = "Тест завершен! Результат: $score / ${questions.size}. Отвечено на вопросов: $answeredQuestions. Время, затраченное на викторину: ${(endTime.value - startTime.value) / 1000} секунд",
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                                .background(Colors.answerbgc, shape = RoundedCornerShape(8.dp))
                                .padding(8.dp),
                            color = Colors.swamp
                        )
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
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
                                    currentQuestionIndex = 0
                                    score = 0
                                    selectedAnswer = ""
                                    answeredQuestions = 0
                                    setIsQuizFinished(false)
                                    questions = QuestionsDataProvider.questions.shuffled(Random.Default).take(10)
                                    startTime.value = System.currentTimeMillis()
                                    endTime.value = 0L
                                },
                                modifier = Modifier.height(32.dp),
                                colors = ButtonDefaults.buttonColors(
                                    Colors.purple
                                )
                            ) {
                                Text(
                                    color = Colors.Yellow,
                                    text = stringResource(id = R.string.retry_quiz)
                                )
                            }
                        }
                    }
                }
            }
        }

    }
}



