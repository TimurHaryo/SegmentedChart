package id.timtam.segmentedchart.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.timtam.segmentedchart.ui.theme.SegmentedChartTheme

@Composable
fun HalfDonutChart(
    modifier: Modifier = Modifier,
    wins: Int,
    losses: Int,
) {
    val total = wins + losses
    val winRatio = wins.toFloat() / total
    val lossRatio = losses.toFloat() / total
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val strokeWidth = 40f
            val startAngle = 180f
            val sweepAngleWins = 180 * winRatio
            val sweepAngleLosses = 180 * lossRatio
            val chartSize = size.minDimension / 2
            // Green arc for wins
            drawArc(
                color = Color.Green,
                startAngle = startAngle,
                sweepAngle = sweepAngleWins,
                useCenter = false,
                size = Size(chartSize, chartSize),
                style = Stroke(strokeWidth, cap = StrokeCap.Round),
            )
            // Red arc for losses (flat start, rounded end)
            // Flat start
            drawArc(
                color = Color.Red,
                startAngle = startAngle + sweepAngleWins,
                sweepAngle = sweepAngleLosses - 1, // Slightly reduced to leave room for rounded cap
                useCenter = false,
                size = Size(chartSize, chartSize),
                style = Stroke(strokeWidth, cap = StrokeCap.Butt),
            )
            // Rounded end
            drawArc(
                color = Color.Red,
                startAngle = startAngle + sweepAngleWins + sweepAngleLosses - 1,
                sweepAngle = 1f, // Small arc to make the end rounded
                useCenter = false,
                size = Size(chartSize, chartSize),
                style = Stroke(strokeWidth, cap = StrokeCap.Round),
            )
        }
        // Display the central text
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = total.toString(),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
            Text(
                text = "Trades",
                fontSize = 16.sp,
                color = Color.Gray,
            )
        }
        // Display wins and losses
        Row(
            Modifier.fillMaxWidth().padding(top = 100.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(horizontalAlignment = Alignment.Start) {
                Text(
                    text = wins.toString(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Green,
                )
                Text(text = "Wins", fontSize = 14.sp, color = Color.Gray)
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = losses.toString(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red,
                )
                Text(text = "Losses", fontSize = 14.sp, color = Color.Gray)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HalfDonutChartPreview() {
    SegmentedChartTheme {
        HalfDonutChart(
            wins = 199,
            losses = 1,
        )
    }
}
