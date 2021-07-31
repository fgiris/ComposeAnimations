package dev.fgiris.composeanimations

import androidx.compose.runtime.Composable
import dev.fgiris.composeanimations.ui.theme.ComposeAnimationsTheme

@Composable
fun ComposeAnimationsApp() {
    ComposeAnimationsTheme {
        ComposeAnimationsNavGraph()
    }
}