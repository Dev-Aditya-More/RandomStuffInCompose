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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MessengerLogo(modifier: Modifier = Modifier, navController: NavController) {

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

        IconButton(
            onClick = {
                isTapped = !isTapped
                navController.navigate(Logos.Photos.route)
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