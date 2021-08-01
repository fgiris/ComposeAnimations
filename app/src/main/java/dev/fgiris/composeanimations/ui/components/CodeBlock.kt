package dev.fgiris.composeanimations.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat
import dev.fgiris.composeanimations.R

@Composable
fun CodeBlock(lines: List<String>) {
    val fontFamily = FontFamily(
        ResourcesCompat.getFont(LocalContext.current, R.font.consolas)!!
    )

    val textStyle = LocalTextStyle.current.copy(
        color = Color.Black,
        fontFamily = fontFamily
    )

    // Text color will always be black
    ProvideTextStyle(value = textStyle) {
        Box(
            modifier = Modifier
                .background(Color(0xFFE0E0E0))
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column {
                lines.forEach {
                    Row {
                        Text(text = it)
                    }
                }
            }
        }
    }
}