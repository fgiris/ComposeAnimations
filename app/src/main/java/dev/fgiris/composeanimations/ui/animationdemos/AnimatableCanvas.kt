package dev.fgiris.composeanimations.ui.animationdemos

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import kotlinx.coroutines.launch

@Composable
fun AnimatableCanvas() {
    // This scope will be canceled when the composable leaves the composition
    val animationScope = rememberCoroutineScope()

    // You could also give an offset as an initial value and
    // Offset.VectorConverter as a typeConverter. For the simplicity
    // lets just use a different float value for x and y
    val animatableX = remember { Animatable(initialValue = 0f) }
    val animatableY = remember { Animatable(initialValue = 0f) }

    val onSurface = MaterialTheme.colors.onSurface

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .clickable {
                animationScope.launch {
                    // Start the animations without blocking each other
                    // On each click x and y values will be created randomly
                    launch {
                        animatableX.animateTo(
                            targetValue = (0..1000)
                                .random()
                                .toFloat(),
                            animationSpec = tween(durationMillis = 1000)
                        )
                    }

                    launch {
                        animatableY.animateTo(
                            targetValue = (0..1000)
                                .random()
                                .toFloat(),
                            animationSpec = tween(durationMillis = 1000)
                        )
                    }
                }
            }
    ) {
        drawCircle(
            color = onSurface,
            radius = 10f,
            center = Offset(
                x = animatableX.value,
                y = animatableY.value
            )
        )
    }
}