package com.example.randomstuffincompose

import android.graphics.ColorFilter
import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun FacebookLogo(modifier: Modifier = Modifier) {

    Canvas(
        modifier
            .padding(12.dp)
            .size(300.dp)
    ) {

        val paint = Paint().apply {
            textAlign = Paint.Align.CENTER
            textSize = 320.dp.toPx()
            color = Color.White.toArgb()
        }
        drawRoundRect(
            color = Color(0xFF1776D1),
            cornerRadius = CornerRadius(150f, 150f)

        )

        drawContext.canvas.nativeCanvas.drawText(
            "f",
            center.x + 80f,
            center.y + 342f,
            paint
        )
    }
}