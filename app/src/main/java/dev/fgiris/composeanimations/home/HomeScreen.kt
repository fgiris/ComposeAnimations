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

private val highLevelAnimationApis = listOf(
    "AnimatedVisibility",
    "AnimatedContent"
)

private val lowLevelAnimationApis = listOf(
    "animate*AsState",
    "updateTransition",
    "rememberInfiniteTransition",
    "Animatable",
    "TargetBasedAnimation",
    "DecayAnimation"
)

@ExperimentalFoundationApi
@Composable
fun HomeScreen() {
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Compose Animations") }) }
    ) {
        HomeScreen(
            highLevelAnimationApis = highLevelAnimationApis,
            lowLevelAnimationApis = lowLevelAnimationApis
        )
    }
}

@ExperimentalFoundationApi
@Composable
fun HomeScreen(
    highLevelAnimationApis: List<String>,
    lowLevelAnimationApis: List<String>
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        AnimationApisList(
            scope = this@LazyColumn,
            header = "High-level Animation APIs",
            apis = highLevelAnimationApis
        )

        item {
            Spacer(modifier = Modifier.height(4.dp))
        }

        AnimationApisList(
            scope = this@LazyColumn,
            header = "Low-level APIs",
            apis = lowLevelAnimationApis
        )
    }
}

@ExperimentalFoundationApi
fun AnimationApisList(
    scope: LazyListScope,
    header: String,
    apis: List<String>
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
            Button(onClick = { /*TODO*/ }) {
                Text(text = item)
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