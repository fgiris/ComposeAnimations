package dev.fgiris.composeanimations.ui.animationdemos

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun RememberInfiniteTransitionCanvas() {
    val infiniteTransition = rememberInfiniteTransition()
    val infinitelyAnimatedFloat = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(),
            // The value will infinitely repeat from 0 to 1 and 1 to 0
            repeatMode = RepeatMode.Reverse
        )
    )

    val onSurface = MaterialTheme.colors.onSurface

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        drawCircle(
            color = onSurface,
            radius = 10f,
            // The circle will blink infinitely with the animation specs above
            alpha = infinitelyAnimatedFloat.value
        )
    }
}