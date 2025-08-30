package com.example.randomstuffincompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.randomstuffincompose.ui.theme.RandomStuffInComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RandomStuffInComposeTheme {


            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InstagramLogo(modifier: Modifier = Modifier) {

    val brush = Brush.linearGradient(
        listOf(Color.Red, Color.Blue)
    )

    Box(
        modifier.background(brush),
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
}
