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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PhotosLogo(modifier: Modifier = Modifier, navController: NavController) {

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
            modifier.padding(16.dp).size(300.dp)
        ) {

            drawArc(
                color = Color(68, 134, 244),
                startAngle = 0f,
                sweepAngle = 180f,
                useCenter = true,
                topLeft = Offset(
                    x = size.width.div(2f),
                    y = size.height.times(.25f)
                ),
                size = Size(
                    size.width.div(2f),
                    size.height.div(2f)
                )
            )

            drawArc(
                color = Color(218, 72, 59),
                startAngle = -90f,
                sweepAngle = 180f,
                useCenter = true,
                topLeft = Offset(
                    x = size.width.times(0.25f),
                    y = 0f
                ),
                size = Size(
                    size.width.div(2f),
                    size.height.div(2f)
                )
            )

            drawArc(
                color = Color(28, 164, 92),
                startAngle = -180f,
                sweepAngle = 180f,
                useCenter = true,
                topLeft = Offset(
                    x = 0f,
                    y = size.height.times(0.256f)
                ),
                size = Size(
                    size.width.div(2f),
                    size.height.div(2f)
                )
            )

            drawArc(
                color = Color(255, 199, 24),
                startAngle = -270f,
                sweepAngle = 180f,
                useCenter = true,
                topLeft = Offset(
                    x = size.width.times(0.25f),
                    y = size.height.times(0.5f)
                ),
                size = Size(
                    size.width.div(2f),
                    size.height.div(2f)
                )
            )
        }

        IconButton(
            onClick = {
                isTapped = !isTapped
                navController.navigate("weather")
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrowforward),
                contentDescription = "Back",
                tint = iconColor
            )
        }
    }
}