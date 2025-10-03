package com.example.randomstuffincompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.randomstuffincompose.ui.theme.RandomStuffInComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RandomStuffInComposeTheme {
                val navController = rememberNavController()
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black),
                    contentAlignment = Alignment.Center
                ) {
                    GlitchEffectImpl()
                }
            }
        }
    }
}

@Composable
fun InstagramLogo(modifier: Modifier = Modifier, navController: NavController) {


    val brush = Brush.linearGradient(
        listOf(Color.Red, Color.Blue)
    )

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
        Box(
            modifier.background(brush)
        ) {
            Canvas(
                modifier
                    .size(300.dp)
                    .padding(50.dp)
            ) {

                drawRoundRect(
                    brush = Brush.linearGradient(
                        listOf(Color.White, Color.White)
                    ),
                    cornerRadius = CornerRadius(150f, 150f),
                    style = Stroke(width = 36f)
                )

                drawCircle(

                    brush = Brush.linearGradient(
                        listOf(Color.White, Color.White)
                    ),
                    radius = 100f,
                    style = Stroke(width = 36f)
                )

                drawCircle(
                    brush = Brush.linearGradient(
                        listOf(Color.White, Color.White)
                    ),
                    radius = 32f,
                    center = Offset(this.size.width * .8f, this.size.height * .2f)
                )
            }
        }

        IconButton(
            onClick = {
                isTapped = !isTapped
                navController.navigate(Logos.Kotlin.route)
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrowforward),
                contentDescription = "Forward",
                tint = iconColor
            )
        }
    }
}
