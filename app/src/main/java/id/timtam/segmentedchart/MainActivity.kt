package id.timtam.segmentedchart

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import id.timtam.segmentedchart.component.SegmentedDonutChart
import id.timtam.segmentedchart.component.layout.OtherStockAllocationLayout
import id.timtam.segmentedchart.component.layout.TopStockAllocationLayout
import id.timtam.segmentedchart.ui.theme.SegmentedChartTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()

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
                    val resource by viewModel.state.collectAsState()
                    val changeOn by remember(resource) {
                        mutableStateOf(
                            when (resource) {
                                is Resource.Loading -> "..."
                                is Resource.Success -> "Change on: ${(resource as Resource.Success).changeOn}"
                            },
                        )
                    }
                    var data by remember {
                        mutableStateOf(Data.stockData.sortedByDescending { it.value })
                    }

                    LaunchedEffect(resource) {
                        when (resource) {
                            is Resource.Loading -> {}
                            is Resource.Success -> {
                                data = (resource as Resource.Success).data
                            }
                        }
                    }

                    Column(
                        modifier =
                            Modifier
                                .padding(top = 64.dp, bottom = innerPadding.calculateBottomPadding())
                                .fillMaxHeight()
                                .verticalScroll(
                                    state = rememberScrollState(),
                                ),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Box(
                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight(),
                            contentAlignment = Alignment.Center,
                        ) {
                            SegmentedDonutChart(
                                segments = data,
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
                                    text = "${data.sumOf { it.value }}",
                                    style =
                                        TextStyle(
                                            fontSize = TextUnit(24f, TextUnitType.Sp),
                                            fontStyle = FontStyle.Normal,
                                            fontWeight = FontWeight.Bold,
                                        ),
                                )

                                Text(
                                    text = "${data.size} Stocks",
                                    style =
                                        TextStyle(
                                            fontSize = TextUnit(14f, TextUnitType.Sp),
                                        ),
                                )

                                Text(
                                    text = changeOn,
                                    style =
                                        TextStyle(
                                            fontSize = TextUnit(10f, TextUnitType.Sp),
                                        ),
                                )
                            }
                        }

                        Button(
                            modifier =
                                Modifier
                                    .padding(
                                        top = 24.dp,
                                    ).wrapContentWidth()
                                    .wrapContentHeight(),
                            onClick = {
                                viewModel.populateData()
                            },
                            content = {
                                Text(
                                    text = "Mock Data",
                                    style =
                                        TextStyle(
                                            fontSize = TextUnit(14f, TextUnitType.Sp),
                                        ),
                                )
                            },
                        )

                        TopStockAllocationLayout(
                            modifier =
                                Modifier
                                    .padding(top = 24.dp),
                            topStocks = Data.top20StocksData,
                        )

                        OtherStockAllocationLayout(
                            otherStock = Data.otherStock,
                        )
                    }
                }
            }
        }
    }
}
