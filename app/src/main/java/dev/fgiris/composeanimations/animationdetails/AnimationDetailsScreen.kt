package dev.fgiris.composeanimations.animationdetails

import android.graphics.Typeface
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat
import dev.fgiris.composeanimations.R
import dev.fgiris.composeanimations.components.CodeBlock
import dev.fgiris.composeanimations.data.AnimationApiType

@Composable
fun AnimationDetailsScreen(animationApi: String) {
    val animationApiType = AnimationApiType.valueOf(animationApi)

    val (titleRes, descriptionRes) = when (animationApiType) {
        AnimationApiType.ANIMATED_VISIBILITY -> Pair(
            R.string.animation_details_animated_visibility_title,
            R.string.animation_details_animated_visibility_description
        )
        AnimationApiType.ANIMATED_CONTENT -> Pair(
            R.string.animation_details_animated_content_title,
            R.string.animation_details_animated_content_description
        )
        AnimationApiType.ANIMATE_AS_STATE -> Pair(
            R.string.animation_details_animate_as_state_title,
            R.string.animation_details_animate_as_state_description
        )
        AnimationApiType.UPDATE_TRANSITION -> Pair(
            R.string.animation_details_update_transition_title,
            R.string.animation_details_update_transition_description
        )
        AnimationApiType.REMEMBER_INFINITE -> Pair(
            R.string.animation_details_remember_infinite_transition_title,
            R.string.animation_details_remember_infinite_transition_description
        )
        AnimationApiType.ANIMATABLE -> Pair(
            R.string.animation_details_animatable_title,
            R.string.animation_details_animatable_description
        )
        AnimationApiType.TARGET_BASED_ANIMATION -> Pair(
            R.string.animation_details_target_based_animation_title,
            R.string.animation_details_target_based_animation_description
        )
        AnimationApiType.DECAY_ANIMATION -> Pair(
            R.string.animation_details_decay_animation_title,
            R.string.animation_details_decay_animation_description
        )
    }

    AnimationDetailsScreen(
        title = stringResource(id = titleRes),
        description = stringResource(id = descriptionRes)
    )
}

@Composable
fun AnimationDetailsScreen(
    title: String,
    description: String
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = title) }) }
    ) {
        LazyColumn(
            contentPadding = PaddingValues(16.dp)
        ) {
            item { AnimationDescription(description = description) }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item {
                AnimationCodeBlock(
                    code = listOf(
                        "val x = 5",
                        "var y = 3"
                    )
                )
            }
        }
    }
}

@Composable
fun AnimationDescription(description: String) {
    Text(
        text = description,
        style = MaterialTheme.typography.body1
    )
}

@Composable
fun AnimationCodeBlock(code: List<String>) {
    CodeBlock(code)
}