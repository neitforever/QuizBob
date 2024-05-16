package com.example.spongebobgame.main_screen

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.spongebobgame.R

@Composable
fun MainScreen(navController: NavController) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE
        val backgroundImageRes = if (isLandscape) R.drawable.pex else R.drawable.vex

        Image(
            painter = painterResource(id = backgroundImageRes),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
        ) {
            if (isLandscape) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(bottom = 100.dp, start = 32.dp)
                    ) {
                        Button(
                            onClick = { navController.navigate("memory_game") },
                            modifier = Modifier
                                .padding(8.dp)
                                .width(200.dp)
                                .height(60.dp),
                            border = BorderStroke(2.dp, Color.Black),
                            contentPadding = PaddingValues(16.dp)
                        ) {
                            Text(text = stringResource(id = R.string.memory_game), fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        }
                        Button(
                            onClick = { navController.navigate("quiz_game") },
                            modifier = Modifier
                                .padding(8.dp)
                                .width(200.dp)
                                .height(60.dp),
                            border = BorderStroke(2.dp, Color.Black),
                            contentPadding = PaddingValues(16.dp)
                        ) {
                            Text(text = stringResource(id = R.string.quiz_game), fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
            else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(bottom = 32.dp)
                    ) {
                        Button(
                            onClick = { navController.navigate("memory_game") },
                            modifier = Modifier
                                .padding(8.dp)
                                .width(300.dp)
                                .height(60.dp),
                            border = BorderStroke(2.dp, Color.Black),
                            contentPadding = PaddingValues(16.dp)
                        ) {
                            Text(text = stringResource(id = R.string.memory_game), fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        }
                        Button(
                            onClick = { navController.navigate("quiz_game") },
                            modifier = Modifier
                                .padding(8.dp)
                                .width(300.dp)
                                .height(60.dp),
                            border = BorderStroke(2.dp, Color.Black),
                            contentPadding = PaddingValues(16.dp)
                        ) {
                            Text(text = stringResource(id = R.string.quiz_game), fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

