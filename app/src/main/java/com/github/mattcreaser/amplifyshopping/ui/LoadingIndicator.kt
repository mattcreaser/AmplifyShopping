package com.github.mattcreaser.amplifyshopping.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.mattcreaser.amplifyshopping.ui.theme.AppTheme

@Composable
private fun LoadingDot(
    modifier: Modifier = Modifier,
    color: Color
) {
    Box(
        modifier = modifier
            .clip(shape = CircleShape)
            .background(color = color)
    )
}

@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier,
    color: Color = AppTheme.colors.primary
) {
    val dotSize = AppTheme.dimens.grid_1_5
    val magnitude = dotSize.value / 2
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.grid_1)
    ) {
        for (i in 0 until 3) {
            val transition = rememberInfiniteTransition()
            val offset by transition.animateFloat(
                initialValue = magnitude,
                targetValue = -magnitude,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = 450),
                    repeatMode = RepeatMode.Reverse,
                    initialStartOffset = StartOffset(
                        offsetMillis = i * 150,
                        StartOffsetType.FastForward
                    )
                )
            )
            LoadingDot(
                color = color,
                modifier = Modifier
                    .size(dotSize)
                    .aspectRatio(1f)
                    .offset(y = offset.dp)
            )
        }
    }
}