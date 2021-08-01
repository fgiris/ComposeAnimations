package dev.fgiris.composeanimations.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.fgiris.composeanimations.MainActions
import dev.fgiris.composeanimations.data.AnimationApiType

private val highLevelAnimationApis = listOf(
    Pair("AnimatedVisibility", AnimationApiType.ANIMATED_VISIBILITY),
    Pair("AnimatedContent", AnimationApiType.ANIMATED_CONTENT)
)

private val lowLevelAnimationApis = listOf(
    Pair("animate*AsState", AnimationApiType.ANIMATE_AS_STATE),
    Pair("updateTransition", AnimationApiType.UPDATE_TRANSITION),
    Pair("rememberInfiniteTransition", AnimationApiType.REMEMBER_INFINITE),
    Pair("Animatable", AnimationApiType.ANIMATABLE),
    Pair("TargetBasedAnimation", AnimationApiType.TARGET_BASED_ANIMATION),
    Pair("DecayAnimation", AnimationApiType.DECAY_ANIMATION)
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
    highLevelAnimationApis: List<Pair<String, AnimationApiType>>,
    lowLevelAnimationApis: List<Pair<String, AnimationApiType>>,
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
    apis: List<Pair<String, AnimationApiType>>,
    navActions: MainActions
) {
    scope.apply {
        stickyHeader {
            Text(
                text = header,
                style = MaterialTheme.typography.h5
            )
        }

        item { AnimationListDivider() }

        items(apis) { item ->
            Button(
                onClick = {
                    navActions.navigateToAnimationDetails(item.second)
                }
            ) {
                Text(text = item.first)
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