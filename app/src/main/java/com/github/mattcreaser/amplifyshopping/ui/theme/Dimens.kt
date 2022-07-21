package com.github.mattcreaser.amplifyshopping.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class Dimens(
    val grid_0_25: Dp = 2.dp,
    val grid_0_5: Dp = 4.dp,
    val grid_1: Dp = 8.dp,
    val grid_1_5: Dp = 12.dp,
    val grid_2: Dp = 16.dp,
    val grid_2_5: Dp = 20.dp,
    val grid_3: Dp = 24.dp,
    val grid_3_5: Dp = 28.dp,
    val grid_4: Dp = 32.dp,
    val grid_4_5: Dp = 36.dp,
    val grid_5: Dp = 40.dp,
    val grid_5_5: Dp = 44.dp,
    val grid_6: Dp = 48.dp
)

val LocalDimens = staticCompositionLocalOf { Dimens() }