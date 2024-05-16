package com.example.spongebobgame.ui.theme

import androidx.compose.ui.graphics.Color
import com.example.spongebobgame.R

class SpongeBobTheme(
    val backgroundPortrait: Int = R.drawable.background_vertical_table,
    val backgroundLandscape: Int = R.drawable.background_horizontal_table,
    val cardback: Int = R.drawable.cardback_flowers,
    val cardBaseColor: Color = Colors.Tan,
    val cardFrontBaseColor: Color = Colors.Beige,
    val cardFrontBorderColor: Color = Colors.Purpure,
    val matchedOutlineColor: Color = Colors.Green,
    val iconColor: Color = Colors.Brown,
    val cardbackBorder: Color= Colors.DarkGreen,
    val imageMap: Map<Int, Int> = mapOf(
        1 to R.drawable.p1,
        2 to R.drawable.p2,
        3 to R.drawable.p3,
        4 to R.drawable.p4,
        5 to R.drawable.p5,
        6 to R.drawable.p6,
        7 to R.drawable.p7,
        8 to R.drawable.p8,
        9 to R.drawable.p9,
    )
) {
    fun getImageResourceForNumber(number: Int): Int? {
        return imageMap[number]
    }
}
