package id.timtam.segmentedchart.component.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.timtam.segmentedchart.Data
import id.timtam.segmentedchart.component.StockAllocationItem
import id.timtam.segmentedchart.data.StockAllocationItemUIState
import id.timtam.segmentedchart.ui.theme.SegmentedChartTheme

@Composable
fun TopStockAllocationLayout(
    modifier: Modifier = Modifier,
    stocks: List<StockAllocationItemUIState>,
) {
    Column(
        modifier =
            modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
    ) {
        stocks.forEach { stock ->
            key(stock.symbol) {
                StockAllocationItem(
                    item = stock,
                    showProgressBar = true,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopStockAllocationLayoutPreview() {
    SegmentedChartTheme {
        TopStockAllocationLayout(
            stocks = Data.top20StocksData,
        )
    }
}
