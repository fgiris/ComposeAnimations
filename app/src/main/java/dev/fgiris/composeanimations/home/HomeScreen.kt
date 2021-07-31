package dev.fgiris.composeanimations.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private val highLevelApis = listOf(
    "AnimatedVisibility",
    "AnimatedContent"
)

private val lowLevelApis = listOf(
    "animate*AsState",
    "updateTransition",
    "rememberInfiniteTransition",
    "Animatable",
    "TargetBasedAnimation",
    "DecayAnimation"
)

@Composable
fun HomeScreen() {
    Surface(color = MaterialTheme.colors.background) {
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                Text(text = "High-level APIs")
            }

            items(highLevelApis) { item ->
                Button(onClick = { /*TODO*/ }) {
                    Text(text = item)
                }
            }

            item {
                Text(text = "Low-level APIs")
            }

            items(lowLevelApis) { item ->
                Button(onClick = { /*TODO*/ }) {
                    Text(text = item)
                }
            }
        }
    }
}