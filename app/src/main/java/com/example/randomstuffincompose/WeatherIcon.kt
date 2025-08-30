package com.example.randomstuffincompose

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun WeatherIcon(modifier: Modifier = Modifier) {

    Canvas(
        modifier.size(300.dp).padding(16.dp)
    ) {

        val weatherPath = Path().apply {
            moveTo(
                size.width.times(0.45f),
                size.height.times(0.75f)
            )
            cubicTo(
                size.width.times(0.25f),
                size.height.times(0.75f),
                size.width.times(0.25f),
                size.height.times(0.45f),
                size.width.times(0.45f),
                size.height.times(0.5f)
            )

            cubicTo(
                size.width.times(0.38f),
                size.height.times(0.2f),
                size.width.times(0.75f),
                size.height.times(0.15f),
                size.width.times(0.75f),
                size.height.times(0.4f)
            )

            cubicTo(
                size.width.times(0.95f),
                size.height.times(0.35f),
                size.width.times(0.95f),
                size.height.times(0.75f),
                size.width.times(0.75f),
                size.height.times(0.75f)
            )
        }

        drawRoundRect(
            brush = Brush.linearGradient(
                listOf(
                    Color(29, 113, 242),
                    Color(28, 156, 246),
                    Color(25, 195, 251)
                )
            ),
            size = size,
            cornerRadius = CornerRadius(130f, 130f)
        )

        drawCircle(
            color = Color(255, 205, 0),
            radius = size.width.times(0.2f),
            center = Offset(
                size.width.times(0.35f),
                size.height.times(0.37f)
            )
        )

        drawPath(
            path = weatherPath,
            color = Color.White.copy(alpha = 0.86f),
            style = Fill
        )
    }
}