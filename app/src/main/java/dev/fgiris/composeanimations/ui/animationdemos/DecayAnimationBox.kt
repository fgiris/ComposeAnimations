package dev.fgiris.composeanimations.ui.animationdemos

import androidx.compose.animation.core.DecayAnimation
import androidx.compose.animation.core.FloatExponentialDecaySpec
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.fgiris.composeanimations.R
import kotlinx.coroutines.launch

@Composable
fun DecayAnimationBox() {
    val decayAnimation = remember {
        DecayAnimation(
            animationSpec = FloatExponentialDecaySpec(
                // How quick the animation will stop
                frictionMultiplier = 0.7f
            ),
            initialValue = 0f,
            initialVelocity = 600f
        )
    }

    // We will manually handle the play time of the animation
    var playTime by remember { mutableStateOf(0L) }

    val animationScope = rememberCoroutineScope()
    var animationState by remember { mutableStateOf(DecayAnimationState.PAUSED) }
    var animationValue by remember { mutableStateOf(0f) }

    val onClick: () -> Unit = {
        // Toggle animation state
        animationState = when (animationState) {
            DecayAnimationState.RUNNING -> DecayAnimationState.PAUSED
            DecayAnimationState.PAUSED -> DecayAnimationState.RUNNING
        }

        animationScope.launch {
            // Need to extract the already played time
            // If user pauses and resumes the animation, shifting start time by
            // play time will make sure that the animation will continue from the
            // last played time
            var startTime = withFrameNanos { it } - playTime

            // Only continue animating if the state is running
            while (animationState == DecayAnimationState.RUNNING) {
                // If the animation is already ended then reset the start time
                // in order animation to start again
                if (decayAnimation.isFinishedFromNanos(playTime)) startTime = withFrameNanos { it }

                playTime = withFrameNanos { it } - startTime
                animationValue = decayAnimation.getValueFromNanos(playTime)
            }
        }
    }

    Box(
        modifier = Modifier
            .clickable(onClick = onClick)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_shopping_cart),
            contentDescription = "",
            modifier = Modifier.offset(
                x = animationValue.dp,
                y = 250.dp
            )
        )
    }
}

enum class DecayAnimationState {
    RUNNING, PAUSED
}