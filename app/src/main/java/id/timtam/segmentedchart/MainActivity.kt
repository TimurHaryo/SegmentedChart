package id.timtam.segmentedchart

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import id.timtam.segmentedchart.component.SegmentedDonutChart
import id.timtam.segmentedchart.ui.theme.SegmentedChartTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SegmentedChartTheme {
                Scaffold(
                    Modifier
                        .background(Color.Transparent)
                        .fillMaxSize(),
                ) { innerPadding ->
                    Box(
                        modifier =
                            Modifier
                                .padding(innerPadding)
                                .fillMaxWidth()
                                .fillMaxWidth(),
                        contentAlignment = Alignment.Center,
                    ) {
                        SegmentedDonutChart(
                            segments = Data.stockData,
                            segmentThickness = 24f,
                            segmentGaps = 2.5f,
                            animationDurationMillis = 500,
                        )

                        Column(
                            modifier =
                                Modifier
                                    .wrapContentSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(
                                text = "${Data.stockData.sumOf { it.value }}",
                                style =
                                    TextStyle(
                                        fontSize = TextUnit(24f, TextUnitType.Sp),
                                        fontStyle = FontStyle.Normal,
                                        fontWeight = FontWeight.Bold,
                                    ),
                            )

                            Text(
                                text = "${Data.stockData.size} Stocks",
                                style =
                                    TextStyle(
                                        fontSize = TextUnit(14f, TextUnitType.Sp),
                                    ),
                            )
                        }
                    }
                }
            }
        }
    }
}
