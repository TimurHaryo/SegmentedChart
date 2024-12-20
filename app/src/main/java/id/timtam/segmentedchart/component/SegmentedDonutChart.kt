package id.timtam.segmentedchart.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastSumBy
import id.timtam.segmentedchart.Data
import id.timtam.segmentedchart.component.data.ArcData
import id.timtam.segmentedchart.data.StockAllocationChartUIState
import id.timtam.segmentedchart.extension.addRoundedEdgeBothSides
import id.timtam.segmentedchart.extension.fromHex
import id.timtam.segmentedchart.ui.theme.SegmentedChartTheme
import kotlinx.coroutines.launch
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun SegmentedDonutChart(
    modifier: Modifier = Modifier,
    segments: List<StockAllocationChartUIState>,
    segmentEdgeRadius: Float = 4f,
    segmentGaps: Float = 4f,
    segmentThickness: Float = 20f,
    animationDurationMillis: Int = 0,
) {
    val gaps = segmentGaps / 2
    val total = segments.fastSumBy { it.value }.toFloat()
    var currentAngle = -90f
    var isInitial by remember { mutableStateOf(true) }
    val arcs =
        remember(segments) {
            segments.map { data ->
                val sweepAngle = 360f * (data.value / total) - gaps
                val startAngle = currentAngle
                currentAngle += sweepAngle + gaps
                val anim =
                    Animatable(
                        initialValue =
                            when {
                                isInitial -> {
                                    0f
                                }
                                !isInitial && data.valueAnimation.animate.not() -> {
                                    sweepAngle
                                }
                                else -> {
                                    data.valueAnimation.value
                                }
                            },
                        visibilityThreshold = Spring.DampingRatioHighBouncy,
                    )
                ArcData(
                    targetSweepAngle = sweepAngle,
                    animation = anim,
                    startAngle = startAngle,
                    color = Color.fromHex(data.color),
                )
            }
        }

    LaunchedEffect(arcs) {
        arcs.mapIndexed { index, it ->
            launch {
                it.animation.animateTo(
                    targetValue = it.targetSweepAngle,
                    animationSpec =
                        tween(
                            durationMillis = animationDurationMillis,
                            easing = FastOutSlowInEasing,
                            delayMillis = index,
                        ),
                )
            }
        }
        if (isInitial) {
            isInitial = false
        }
    }

    Canvas(
        modifier = modifier.size(160.dp),
    ) {
        val canvasSize = size.minDimension
        val outerRadius = (canvasSize - segmentThickness) / 2
        val innerRadius = outerRadius - segmentThickness

        arcs.map { data ->
            val sweepAngle = data.animation.value

            val startRadians = Math.toRadians(data.startAngle.toDouble())
            val cornerRadius =
                if (sweepAngle > segmentEdgeRadius) {
                    segmentEdgeRadius
                } else {
                    sweepAngle
                }.times(2)

            val segmentPath =
                Path().apply {
                    val startOuterX = center.x + outerRadius * cos(startRadians).toFloat()
                    val startOuterY = center.y + outerRadius * sin(startRadians).toFloat()

                    moveTo(startOuterX, startOuterY)

                    addRoundedEdgeBothSides(
                        center = Offset(center.x, center.y),
                        startAngleDegrees = data.startAngle,
                        sweepAngleDegrees = sweepAngle,
                        innerRadius = innerRadius,
                        outerRadius = outerRadius,
                        cornerRadius = cornerRadius,
                    )
                }

            drawPath(
                path = segmentPath,
                color = data.color,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SegmentedDonutChartPreview() {
    SegmentedChartTheme {
        SegmentedDonutChart(
            segments = Data.stockData,
            segmentThickness = 24f,
            segmentGaps = 2.5f,
            segmentEdgeRadius = 4f,
            animationDurationMillis = 200,
        )
    }
}
