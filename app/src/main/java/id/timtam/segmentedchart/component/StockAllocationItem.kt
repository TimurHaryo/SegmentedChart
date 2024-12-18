package id.timtam.segmentedchart.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.timtam.segmentedchart.R
import id.timtam.segmentedchart.data.StockAllocationItemUIState
import id.timtam.segmentedchart.extension.fromHex
import id.timtam.segmentedchart.ui.theme.SegmentedChartTheme

@Composable
fun StockAllocationItem(
    item: StockAllocationItemUIState,
    showProgressBar: Boolean = true,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 12.dp,
                ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val iconModifier =
            remember(showProgressBar) {
                if (showProgressBar) {
                    Modifier.size(28.dp)
                } else {
                    Modifier.size(20.dp)
                }
            }
        Box(
            modifier =
                iconModifier
                    .clip(CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter =
                    rememberVectorPainter(
                        ImageVector.vectorResource(R.drawable.ic_launcher_background),
                    ),
                contentDescription = null,
                contentScale = ContentScale.Fit,
            )
        }

        Column(
            modifier =
                Modifier
                    .padding(start = 12.dp),
        ) {
            Row(
                modifier =
                    Modifier
                        .wrapContentSize(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = item.symbol,
                    fontSize = 12.sp,
                    color = Color.White,
                )
                Text(
                    text = item.value,
                    fontSize = 12.sp,
                    color = Color.White,
                    modifier =
                        Modifier
                            .padding(start = 4.dp)
                            .weight(1f),
                )
                Text(
                    text = item.percentageText,
                    fontSize = 12.sp,
                    color = Color.White,
                )
            }

            if (showProgressBar) {
                LinearProgressIndicator(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 6.dp),
                    progress = { item.percentage },
                    color = Color.fromHex(item.color),
                    trackColor = Color(0xFF1F1F1F),
                    strokeCap = StrokeCap.Round,
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF030303)
@Composable
fun StockAllocationItemPreview1() {
    SegmentedChartTheme(darkTheme = true) {
        StockAllocationItem(
            item =
                StockAllocationItemUIState(
                    symbol = "ADRO",
                    value = "Rp27,000,000",
                    percentageText = "25.00%",
                    percentage = 0.25f,
                    color = "0BA16B",
                ),
            showProgressBar = true,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun StockAllocationItemPreview2() {
    SegmentedChartTheme(darkTheme = true) {
        StockAllocationItem(
            item =
                StockAllocationItemUIState(
                    symbol = "ADRO",
                    value = "Rp27,000,000",
                    percentageText = "25.00%",
                    percentage = 0.25f,
                    color = "0BA16B",
                ),
            showProgressBar = false,
        )
    }
}
