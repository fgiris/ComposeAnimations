package dev.fgiris.composeanimations

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import dev.fgiris.composeanimations.ui.theme.ComposeAnimationsTheme

@Composable
fun ComposeAnimationsApp() {
    val scaffoldState = rememberScaffoldState()

    ComposeAnimationsTheme {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar {
                    Text(text = "Compose Animations")
                }
            }
        ) {
            ComposeAnimationsNavGraph()
        }
    }
}