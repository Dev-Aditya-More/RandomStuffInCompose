package com.example.randomstuffincompose

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FacebookLogo(modifier: Modifier = Modifier, navController: NavController) {

    Row(
        modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Canvas(
            modifier
                .then(modifier)
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

        IconButton(
            onClick = {
                navController.navigate(Logos.Instagram.route)
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrowforward),
                contentDescription = "forward",
                tint = Color.White
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FacebookPreview(modifier: Modifier = Modifier) {

    Scaffold { innerPadding ->
        FacebookLogo(
            modifier.padding(innerPadding),
            navController = NavController(LocalContext.current)
        )
    }
}