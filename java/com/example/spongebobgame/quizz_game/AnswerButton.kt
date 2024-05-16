package com.example.spongebobgame.quizz_game

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spongebobgame.ui.theme.Colors

@Composable
fun AnswerButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textColor: Color
) {
    val orientation = LocalConfiguration.current.orientation
    val buttonModifier = if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
        modifier
            .fillMaxWidth(0.6f)
            .height(34.dp)
    } else {
        modifier
            .fillMaxWidth()
            .height(50.dp)
    }

    Button(
        onClick = onClick,
        modifier = buttonModifier,
        colors = ButtonDefaults.buttonColors(
            Colors.answerbgc,
            contentColor = textColor
        )
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            maxLines = 1
        )
    }
}
