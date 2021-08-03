package dev.fgiris.composeanimations.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.fgiris.composeanimations.MainActions
import dev.fgiris.composeanimations.data.AnimationApiType

private val highLevelAnimationApis = listOf(
    Triple(
        "AnimatedVisibility",
        AnimationApiType.ANIMATED_VISIBILITY,
        "Animate the visibility of a composable"
    ),
    Triple(
        "AnimatedContent",
        AnimationApiType.ANIMATED_CONTENT,
        "Animate the whole content of a composable"
    )
)

private val lowLevelAnimationApis = listOf(
    Triple(
        "animate*AsState",
        AnimationApiType.ANIMATE_AS_STATE,
        "Animate using common data types like Int, Float etc. as state"
    ),
    Triple(
        "updateTransition",
        AnimationApiType.UPDATE_TRANSITION,
        "Animate using a custom target state"
    ),
    Triple("rememberInfiniteTransition", AnimationApiType.REMEMBER_INFINITE, "Infinite animations"),
    Triple(
        "Animatable",
        AnimationApiType.ANIMATABLE,
        "Animations which are not dependent to composition"
    ),
    Triple(
        "TargetBasedAnimation",
        AnimationApiType.TARGET_BASED_ANIMATION,
        "Control the animation timing"
    ),
    Triple(
        "DecayAnimation",
        AnimationApiType.DECAY_ANIMATION,
        "Stateless animations with given play time"
    )
)

@ExperimentalFoundationApi
@Composable
fun HomeScreen(navActions: MainActions) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Compose Animations") }) }
    ) {
        HomeScreen(
            highLevelAnimationApis = highLevelAnimationApis,
            lowLevelAnimationApis = lowLevelAnimationApis,
            navActions = navActions
        )
    }
}

@ExperimentalFoundationApi
@Composable
fun HomeScreen(
    highLevelAnimationApis: List<Triple<String, AnimationApiType, String>>,
    lowLevelAnimationApis: List<Triple<String, AnimationApiType, String>>,
    navActions: MainActions
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        AnimationApisList(
            scope = this@LazyColumn,
            header = "High-level Animation APIs",
            apis = highLevelAnimationApis,
            navActions = navActions
        )

        item {
            Spacer(modifier = Modifier.height(4.dp))
        }

        AnimationApisList(
            scope = this@LazyColumn,
            header = "Low-level APIs",
            apis = lowLevelAnimationApis,
            navActions = navActions
        )
    }
}

@ExperimentalFoundationApi
fun AnimationApisList(
    scope: LazyListScope,
    header: String,
    apis: List<Triple<String, AnimationApiType, String>>,
    navActions: MainActions
) {
    scope.apply {
        stickyHeader {
            Text(
                text = header,
                style = MaterialTheme.typography.subtitle1
            )
        }

        item { AnimationListDivider() }

        items(apis) { item ->
            Column(
                modifier = Modifier
                    .padding(bottom = 4.dp)
                    .clickable { navActions.navigateToAnimationDetails(item.second) }
            ) {
                Row {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        text = item.first,
                        style = MaterialTheme.typography.body1
                    )
                }
                Row {
                    Text(
                        text = item.third,
                        style = MaterialTheme.typography.body2.copy(
                            color = Color(0xFF757575)
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun AnimationListDivider() {
    Divider(
        modifier = Modifier.padding(bottom = 4.dp),
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
    )
}