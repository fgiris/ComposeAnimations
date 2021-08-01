package dev.fgiris.composeanimations.ui.animationdemos

import androidx.compose.animation.core.TargetBasedAnimation
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import kotlinx.coroutines.launch

@Composable
fun TargetBasedAnimationCanvas() {
    val targetBasedAnimation = remember {
        TargetBasedAnimation(
            animationSpec = tween(2000),
            typeConverter = Float.VectorConverter,
            initialValue = 0f,
            targetValue = 1000f
        )
    }

    // We will manually handle the play time of the animation
    var playTime = remember { 0L }

    // Do not use LaunchedEffect for the callback events such as when user clicks
    // to start the animation again. Instead use rememberCoroutineScope.
    val animationScope = rememberCoroutineScope()

    var animationState by remember { mutableStateOf(TargetAnimationState.PAUSED) }
    var animationValue by remember { mutableStateOf(0f) }

    val onSurface = MaterialTheme.colors.onSurface

    val onClick: () -> Unit = {
        // Toggle animation state
        animationState = when (animationState) {
            TargetAnimationState.RUNNING -> TargetAnimationState.PAUSED
            TargetAnimationState.PAUSED -> TargetAnimationState.RUNNING
        }

        animationScope.launch {
            // Need to extract the already played time
            // If user pauses and resumes the animation, shifting start time by
            // play time will make sure that the animation will continue from the
            // last played time
            val startTime = withFrameNanos { it } - playTime

            // Only continue animating if the state is running
            while (animationState == TargetAnimationState.RUNNING) {
                playTime = withFrameNanos { it } - startTime
                animationValue = targetBasedAnimation.getValueFromNanos(playTime)
            }
        }
    }

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .clickable(onClick = onClick)
    ) {
        drawCircle(
            color = onSurface,
            radius = 10f,
            center = Offset(
                x = animationValue,
                y = animationValue
            )
        )
    }
}

enum class TargetAnimationState {
    RUNNING, PAUSED
}