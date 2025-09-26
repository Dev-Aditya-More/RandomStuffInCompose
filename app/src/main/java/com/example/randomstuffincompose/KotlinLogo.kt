package com.example.randomstuffincompose

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun KotlinLogo(modifier: Modifier = Modifier) {
    Canvas(
        modifier
            .padding(16.dp)
            .size(300.dp)
    ) {
        val width = size.width
        val height = size.height

        // Full square background
        drawRect(
            brush = Brush.linearGradient(
                listOf(
                    Color(0xFF7F52FF),
                    Color(0xFF0095D5),
                    Color(0xFFFF8C00),
                    Color(0xFFFFCB00)
                ),
                start = Offset(0f, 0f),
                end = Offset(width, height)
            ),
            size = size
        )
    }
}
