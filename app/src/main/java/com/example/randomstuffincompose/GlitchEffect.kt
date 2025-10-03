package com.example.randomstuffincompose

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.layer.drawLayer
import androidx.compose.ui.graphics.rememberGraphicsLayer
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.randomstuffincompose.ui.theme.Cyan500
import com.example.randomstuffincompose.ui.theme.Green500
import com.example.randomstuffincompose.ui.theme.Pink500
import com.example.randomstuffincompose.ui.theme.Red500
import com.example.randomstuffincompose.ui.theme.Yellow500
import com.example.randomstuffincompose.ui.theme.Zinc900
import com.example.randomstuffincompose.ui.theme.Zinc950
import kotlin.math.roundToInt
import kotlin.random.Random
import kotlin.random.nextInt

@Composable
fun Modifier.glitchEffect(
    key: Any? = null,
    glitchColors: List<Color> = listOf(Green500),
    slices: Int = 20,
): Modifier {

    val graphicsLayer = rememberGraphicsLayer()
    var step by remember { mutableIntStateOf(0) }

    LaunchedEffect(key) {
        Animatable(1f)
            .animateTo(
                targetValue = 10f,
                tween(durationMillis = 500, easing = LinearEasing)
            ) {
                if (step != this.value.roundToInt())
                    step = this.value.roundToInt()
            }
        step = 0
    }

    return drawWithContent {
        if (step == 0) {
            drawContent()
            return@drawWithContent
        }
        graphicsLayer.record { this@drawWithContent.drawContent() }

        for (i in 0 until slices) {
            translate(
                left = if (Random.nextInt(8) > step)
                    Random.nextInt(-20..20).toFloat()
                else
                    0f
            ) {
                scale(
                    scaleY = 1f,
                    scaleX = if (Random.nextInt(13) > step) 2f * Random.nextFloat() else 1f
                ) {
                    clipRect(
                        top = (i / slices.toFloat()) * size.height,
                        bottom = (((i + 1) / slices.toFloat()) * size.height) + 1f,
                    ) {
                        drawLayer(graphicsLayer)
                        if (Random.nextInt(10) > step) {
                            drawRect(
                                color = glitchColors.random(),
                                style = Stroke(width = 10f)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GlitchEffectImpl() {

    var key by remember { mutableIntStateOf(0) }
    val interaction = remember { MutableInteractionSource() }
    val isHovered by interaction.collectIsHoveredAsState()

    Box(
        modifier = Modifier
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        key = Random.nextInt()
                    }
                )
            }
            .pointerHoverIcon(PointerIcon.Hand)
            .hoverable(interaction)
            .glitchEffect(
                key,
                remember { listOf(Cyan500, Yellow500, Pink500) })
            .padding(4.dp)
            .rings(
                ringSpace = if (isHovered) 4.dp else 2.dp
            )
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Zinc950, Zinc900)
                ),
                shape = CutCornerShape(20),
            )
            .padding(horizontal = 32.dp, vertical = 16.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Tap to Glitch",
            style = TextStyle(
                color = Red500
            )
        )
    }

}

@Composable
fun Modifier.rings(
    ringColor: Color = Red500,
    ringCount: Int = 6,
    ringSpace: Dp = 2.dp
): Modifier {

    val animatedRingSpace by animateDpAsState(
        targetValue = ringSpace,
        animationSpec = tween()
    )

    return (1..ringCount).map { index ->
        Modifier.border(
            width = 0.dp,
            color = ringColor.copy(alpha = index / ringCount.toFloat()),
            shape = CutCornerShape(20),
        )
            .padding(animatedRingSpace)
    }.fold(initial = this) { acc, item -> acc.then(item) }
}