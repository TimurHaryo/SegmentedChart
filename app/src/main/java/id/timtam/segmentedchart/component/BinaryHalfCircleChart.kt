package id.timtam.segmentedchart.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.timtam.segmentedchart.extension.addRoundedEdgeEndSide
import id.timtam.segmentedchart.extension.addRoundedEdgeStartSide
import id.timtam.segmentedchart.ui.theme.SegmentedChartTheme

@Composable
fun BinaryHalfCircleChart(
    modifier: Modifier = Modifier,
    value1: Int,
    value2: Int,
    thickness: Float = 36f,
) {
    BoxWithConstraints(
        modifier = Modifier
            .wrapContentSize(),
        contentAlignment = Alignment.Center
    ) {

        Canvas(
            modifier = modifier
                .widthIn(
                    min = 112.dp
                )
                .heightIn(
                    min = 56.dp,
                )
        ) {
            val width = constraints.maxWidth
            val height = constraints.maxHeight.toFloat()
            val sweepAngle = 180f
            val startAngle = 180f
            val canvasSize = size.minDimension
            val outerRadius = canvasSize // Outer radius of the ring
            val innerRadius = (canvasSize - thickness) // Inner radius of the ring
            val cornerRadius = thickness / 2f
            val total = value1 + value2
            var currentAngle = startAngle

            val path1 = Path().apply {
                addRoundedEdgeStartSide(
                    center = Offset(width / 2f, height),
                    startAngleDegrees = currentAngle,
                    sweepAngleDegrees = value1 * (sweepAngle) / total,
                    innerRadius = innerRadius,
                    outerRadius = outerRadius,
                    cornerRadius = cornerRadius
                )
            }

            currentAngle += value1 * (sweepAngle) / total

            val path2 = Path().apply {
                addRoundedEdgeEndSide(
                    center = Offset(width / 2f, height),
                    startAngleDegrees = currentAngle,
                    sweepAngleDegrees = value2 * (sweepAngle) / total,
                    innerRadius = innerRadius,
                    outerRadius = outerRadius,
                    cornerRadius = cornerRadius
                )
            }

            drawPath(path1, Color.Green)
            drawPath(path2, Color.Red)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BinaryHalfCircleChartPreview() {
    SegmentedChartTheme {
        BinaryHalfCircleChart(
            value1 = 170,
            value2 = 30,
        )
    }
}