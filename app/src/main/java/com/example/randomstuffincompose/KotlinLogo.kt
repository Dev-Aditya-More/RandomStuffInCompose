package com.example.randomstuffincompose

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun KotlinLogo(modifier: Modifier = Modifier, navController: NavController) {

    var isTapped by remember {
        mutableStateOf(false)
    }

    val iconColor by animateColorAsState(
        targetValue = if (isTapped) Color.DarkGray else Color.DarkGray.copy(alpha = 0.5f),
        animationSpec = tween(durationMillis = 600),
        label = "IconColorAnimation"
    )

    Row(
        modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                isTapped = !isTapped
                navController.popBackStack()
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrayback),
                contentDescription = "Back",
                tint = iconColor
            )
        }
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

        IconButton(
            onClick = {
                isTapped = !isTapped
                navController.navigate(Logos.Messenger.route)
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrowforward),
                contentDescription = "forward",
                tint = iconColor
            )
        }
    }
}
