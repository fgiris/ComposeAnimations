package dev.fgiris.composeanimations.ui.animationdemos

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun AnimateFloatAsStateCanvas() {
    // Initially the alpha value will be 0
    val animationTargetState = remember { mutableStateOf(0f) }

    val animatedFloatState = animateFloatAsState(
        // Whenever the target value changes, new animation
        // will start to the new target value
        targetValue = animationTargetState.value,
        animationSpec = tween(durationMillis = 3000)
    )

    val onSurface = MaterialTheme.colors.onSurface

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .clickable {
                // Change the target state to start the animation
                animationTargetState.value = 1f
            }
    ) {
        drawCircle(
            color = onSurface,
            radius = 50f,
            // The circle will fade in with the given animation value
            alpha = animatedFloatState.value
        )
    }
}