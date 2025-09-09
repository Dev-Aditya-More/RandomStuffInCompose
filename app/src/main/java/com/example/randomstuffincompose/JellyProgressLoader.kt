package com.example.randomstuffincompose

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun LampSliderScreen() {

    var isLight by remember { mutableStateOf(false) }
    // Animate between light & dark background colors
    val backgroundColor by animateColorAsState(
        targetValue = if (isLight) Color(0xFFEFB8C8) else Color.Black,
        animationSpec = spring(dampingRatio = Spring.DampingRatioNoBouncy),
        label = "background"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Lamp above slider
            LampTheme(
                modifier = Modifier.height(300.dp).fillMaxWidth(),
                onToggle = { isLight = it } // <-- propagate state properly
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Slider
            LoopSlider(isLight = isLight)
        }
    }
}

@Composable
fun LampTheme(
    modifier: Modifier = Modifier,
    onToggle: (Boolean) -> Unit
) {
    val scope = rememberCoroutineScope()

    // Offset animatable with vector converter
    val offset = remember { Animatable(Offset(0f, 0f), Offset.VectorConverter) }

    var isLight by remember { mutableStateOf(false) }
    val baseLength = 150f

    Canvas(
        modifier = modifier.pointerInput(Unit) {
            detectDragGestures(
                onDrag = { change, dragAmount ->
                    change.consume()
                    val newX = (offset.value.x + dragAmount.x).coerceIn(-200f, 200f)
                    val newY = (offset.value.y + dragAmount.y).coerceIn(0f, 400f)
                    scope.launch {
                        offset.snapTo(Offset(newX, newY))
                    }
                },
                onDragEnd = {
                    if (offset.value.y > 200f) {
                        isLight = !isLight
                        onToggle(isLight)
                    }
                    scope.launch {
                        offset.animateTo(
                            targetValue = Offset(0f, 0f),
                            animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
                        )
                    }
                }
            )
        }
    ) {
        val centerX = size.width / 2

        // Cord
        drawLine(
            color = if(isLight) Color.Black else Color.White,
            start = Offset(centerX, 0f),
            end = Offset(centerX + offset.value.x, baseLength + offset.value.y),
            strokeWidth = 6f
        )

        // Bulb
        drawCircle(
            color = if (isLight) Color.Yellow else Color.White,
            radius = 35f,
            center = Offset(centerX + offset.value.x, baseLength + offset.value.y + 30f)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoopSlider(isLight: Boolean = false) {
    var value by remember { mutableStateOf(.5f) }

    // Center slider horizontally
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Slider(
            modifier = Modifier.width(400.dp),
            value = value,
            onValueChange = { value = it },
            thumb = {}, // you can later replace with custom knob if needed
            track = { sliderPositions ->
                val fraction by remember {
                    derivedStateOf {
                        (value - sliderPositions.valueRange.start) /
                                (sliderPositions.valueRange.endInclusive - sliderPositions.valueRange.start)
                    }
                }

                val topFraction by animateFloatAsState(
                    targetValue = fraction,
                    animationSpec = spring(
                        stiffness = Spring.StiffnessVeryLow,
                        dampingRatio = 0.4f,
                    ),
                    visibilityThreshold = .00001f
                )

                // your custom track drawing logic unchanged
                Box(
                    modifier = Modifier
                        .height(72.dp)
                        .fillMaxWidth()
                        .drawBehind {
                            val iter = 500
                            val height = size.height

                            drawRoundRect(
                                color = if(isLight) Color(0xFF1C1B1F) else Color(0xFFE5E7EB),
                                cornerRadius = CornerRadius(height)
                            )

                            val path = Path()
                            path.moveTo(x = 0f, y = center.y)
                            path.lineTo(x = 90f, y = center.y)

                            val topBandX = size.width * (topFraction * .4f)
                            val topBandY = center.y - ((size.width * .5f) * (1f - fraction))
                            path.cubicTo(
                                x1 = 90f + 200f,
                                y1 = center.y,
                                x2 = (topBandX) - 300f,
                                y2 = topBandY,
                                x3 = topBandX,
                                y3 = topBandY,
                            )

                            val midDistance = PathMeasure().apply {
                                setPath(path, false)
                            }.length

                            path.cubicTo(
                                x1 = (topBandX) + 300f,
                                y1 = topBandY,
                                x2 = ((size.width * fraction) - (height * .5f)) - 300f,
                                y2 = center.y,
                                x3 = (size.width * fraction) - (height * .5f),
                                y3 = center.y
                            )

                            path.lineTo(
                                x = (size.width * fraction),
                                y = center.y - 20f
                            )

                            val measure = PathMeasure()
                            measure.setPath(path, false)

                            (0..iter).map { i ->
                                val x = i.toFloat() / iter
                                val distance = measure.length * x
                                val position = measure.getPosition(distance)
                                val tangent = measure.getTangent(distance)

                                LinePosition(
                                    position, tangent, distance, distance > midDistance
                                )
                            }
                                .sortedBy { if (!it.reversed) it.distance else -it.distance }
                                .forEach { line ->

                                    val rounding = if (line.distance > measure.length - height / 2) {
                                        val t =
                                            (line.distance - (measure.length - height / 2)) / (height / 2)

                                        CubicBezierEasing(1f, 0f, 1f, 1f).transform(t) * (height / 2)
                                    } else if (line.distance < height / 2) {
                                        val t = 1f - (line.distance / (height / 2))

                                        CubicBezierEasing(1f, 0f, 1f, 1f).transform(t) * (height / 2)
                                    } else {
                                        0f
                                    }

                                    val start =
                                        line.position - Offset(0f, height / 2) + Offset(0f, rounding)
                                    val end =
                                        line.position + Offset(0f, height / 2) - Offset(0f, rounding)

                                    val top = Brush.verticalGradient(
                                        Pair(0f, Color(0xFF9CA3AF)),
                                        Pair(.04f, Color(0xFF9CA3AF)),
                                        Pair(.04f, Color(0xFF64748B)),
                                        Pair(.2f, Color(0xFF7A869A)),
                                        Pair(.8f, Color(0xFF7A869A)),
                                        Pair(.96f, Color(0xFF64748B)),
                                        Pair(.96f, Color(0xFF334155)),
                                        Pair(1f, Color(0xFF334155)),
                                        startY = start.y,
                                        endY = end.y,
                                    )

                                    val bottom = Brush.verticalGradient(
                                        0f to Color(0xFF334155),
                                        0.2f to Color(0xFF111827),
                                        0.8f to Color(0xFF111827),
                                        1f to Color(0xFF334155),
                                        startY = start.y,
                                        endY = end.y,
                                    )

                                    drawLine(
                                        brush = if (line.tangent.x > 0f) top else bottom,
                                        start = start,
                                        end = end,
                                        strokeWidth = (size.width / iter.toFloat()) * 1.4f
                                    )

                                    drawLine(
                                        color = lerp(
                                            Color(0xFF7A869A),
                                            Color(0xFFF56565),
                                            (line.distance / measure.length)
                                        ),
                                        blendMode = BlendMode.Overlay,
                                        start = start,
                                        end = end,
                                        strokeWidth = (size.width / iter.toFloat()) * 1.4f
                                    )
                                }
                        }
                )
            }
        )
    }
}

data class LinePosition(
    val position: Offset,
    val tangent: Offset,
    val distance: Float,
    val reversed: Boolean,
)