package dev.fgiris.composeanimations.ui.animationdemos

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun UpdateTransitionCanvas() {
    // Need to remember in order to prevent setting
    // the same state value to the transition during
    // recomposition.
    val animationTargetState = remember {
        mutableStateOf(AnimationState.START)
    }

    // Any state change will trigger animations which
    // are created with this transition to the new state
    val transition = updateTransition(
        targetState = animationTargetState.value
    )

    val circleAlpha = transition.animateFloat(
        transitionSpec = { tween(durationMillis = 3000) }
    ) {
        if (it == AnimationState.START) 0f else 1f
    }

    val circleRadius = transition.animateFloat(
        transitionSpec = { tween(durationMillis = 3000) }
    ) {
        if (it == AnimationState.START) 10f else 50f
    }

    val onSurface = MaterialTheme.colors.onSurface

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        drawCircle(
            color = onSurface,
            radius = circleRadius.value,
            alpha = circleAlpha.value
        )

        // Set animation state value to another state to trigger the animation
        animationTargetState.value = AnimationState.END
    }
}

enum class AnimationState {
    START, END
}