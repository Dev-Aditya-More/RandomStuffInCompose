package com.example.randomstuffincompose

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun MessengerLogo(modifier: Modifier = Modifier) {

    Canvas(
        modifier
            .padding(16.dp)
            .size(300.dp)
    ) {
        val trianglePath = Path().apply {

            moveTo(
                size.width.times(0.2f),
                size.height.times(0.84f)
            )
            lineTo(
                size.width.times(0.2f),
                size.height.times(0.99f)
            )
            lineTo(
                size.width.times(0.3f),
                size.height.times(0.91f)
            )
        }

        val sparkyPath = Path().apply {
            moveTo(
                size.width.times(0.2f), size.height.times(0.6f)
            )
            lineTo(size.width.times(0.45f), size.height.times(.34f))
            lineTo(size.width.times(0.55f), size.height.times(.45f))
            lineTo(size.width.times(0.8f), size.height.times(.33f))
            lineTo(size.width.times(0.55f), size.height.times(.6f))
            lineTo(size.width.times(0.45f), size.height.times(.47f))
            close()
        }
        drawOval(
            brush = Brush.linearGradient(
                listOf(Color(0, 198, 255), Color(0, 122, 255))
            ),
            size = Size(
                width = size.width,
                height = size.height.times(0.95f)
            )
        )

        drawPath(
            path = trianglePath,
            brush = Brush.linearGradient(
                listOf(Color(0, 198, 255), Color(0, 122, 255))
            ),
            style = Fill
        )

        drawPath(
            path = sparkyPath,
            color = Color.White,
            style = Fill
        )
    }
}