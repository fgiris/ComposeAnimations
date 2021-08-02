package dev.fgiris.composeanimations.ui.animationdetails

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.fgiris.composeanimations.R
import dev.fgiris.composeanimations.data.AnimationApiType
import dev.fgiris.composeanimations.data.AnimationCodeSamples
import dev.fgiris.composeanimations.ui.animationdemos.*
import dev.fgiris.composeanimations.ui.components.CodeBlock

@Composable
fun AnimationDetailsScreen(animationApi: String) {
    val animationApiType = AnimationApiType.valueOf(animationApi)

    val (titleRes, descriptionRes, codeSample) = when (animationApiType) {
        AnimationApiType.ANIMATED_VISIBILITY -> Triple(
            R.string.animation_details_animated_visibility_title,
            R.string.animation_details_animated_visibility_description,
            ""
        )
        AnimationApiType.ANIMATED_CONTENT -> Triple(
            R.string.animation_details_animated_content_title,
            R.string.animation_details_animated_content_description,
            ""
        )
        AnimationApiType.ANIMATE_AS_STATE -> Triple(
            R.string.animation_details_animate_as_state_title,
            R.string.animation_details_animate_as_state_description,
            AnimationCodeSamples.animateAsState
        )
        AnimationApiType.UPDATE_TRANSITION -> Triple(
            R.string.animation_details_update_transition_title,
            R.string.animation_details_update_transition_description,
            AnimationCodeSamples.updateTransition
        )
        AnimationApiType.REMEMBER_INFINITE -> Triple(
            R.string.animation_details_remember_infinite_transition_title,
            R.string.animation_details_remember_infinite_transition_description,
            AnimationCodeSamples.rememberInfiniteTransition
        )
        AnimationApiType.ANIMATABLE -> Triple(
            R.string.animation_details_animatable_title,
            R.string.animation_details_animatable_description,
            AnimationCodeSamples.animatable
        )
        AnimationApiType.TARGET_BASED_ANIMATION -> Triple(
            R.string.animation_details_target_based_animation_title,
            R.string.animation_details_target_based_animation_description,
            AnimationCodeSamples.targetBasedAnimation
        )
        AnimationApiType.DECAY_ANIMATION -> Triple(
            R.string.animation_details_decay_animation_title,
            R.string.animation_details_decay_animation_description,
            AnimationCodeSamples.decayAnimation
        )
    }

    AnimationDetailsScreen(
        title = stringResource(id = titleRes),
        description = stringResource(id = descriptionRes),
        animationApiType = animationApiType,
        codeSample = codeSample
    )
}

@Composable
fun AnimationDetailsScreen(
    title: String,
    description: String,
    animationApiType: AnimationApiType,
    codeSample: String
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = title) }) }
    ) {
        LazyColumn(
            contentPadding = PaddingValues(16.dp)
        ) {
            item { AnimationDetailsListHeader(header = "What is it? 🧐") }
            item { AnimationDescription(description = description) }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item { AnimationDetailsListHeader(header = "How does it work? ⌨️") }
            item {
                AnimationCodeBlock(
                    code = codeSample
                )
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item { AnimationDetailsListHeader(header = "Demo 📺") }
            item { AnimationDemo(animationApiType = animationApiType) }
        }
    }
}

@Composable
fun AnimationDetailsListHeader(header: String) {
    Text(
        text = header,
        style = MaterialTheme.typography.h5
    )

    Divider(
        modifier = Modifier.padding(bottom = 8.dp, top = 4.dp),
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
    )
}

@Composable
fun AnimationDescription(description: String) {
    Text(
        text = description,
        style = MaterialTheme.typography.body1
    )
}

@Composable
fun AnimationCodeBlock(code: String) {
    Spacer(modifier = Modifier.height(4.dp))
    CodeBlock(code)
}

@Composable
fun AnimationDemo(animationApiType: AnimationApiType) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    ) {
        when (animationApiType) {
            AnimationApiType.ANIMATED_VISIBILITY -> return
            AnimationApiType.ANIMATED_CONTENT -> return
            AnimationApiType.ANIMATE_AS_STATE -> {
                AnimateFloatAsStateCanvas()
                showTextAtBottom("Click to start animation")
            }
            AnimationApiType.UPDATE_TRANSITION -> UpdateTransitionCanvas()
            AnimationApiType.REMEMBER_INFINITE -> RememberInfiniteTransitionCanvas()
            AnimationApiType.ANIMATABLE -> {
                AnimatableCanvas()
                showTextAtBottom("Click to animate the circle randomly")
            }
            AnimationApiType.TARGET_BASED_ANIMATION -> {
                TargetBasedAnimationCanvas()
                showTextAtBottom("Click to start and stop the animation")
            }
            AnimationApiType.DECAY_ANIMATION -> {
                DecayAnimationBox()
                showTextAtBottom("Click to start and stop the animation")
            }
        }
    }
}

@Composable
fun BoxScope.showTextAtBottom(text: String) {
    Text(
        modifier = Modifier.align(Alignment.TopStart),
        text = text,
        style = MaterialTheme.typography.caption
    )
}