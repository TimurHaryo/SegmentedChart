package id.timtam.segmentedchart.component.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import id.timtam.segmentedchart.R
import id.timtam.segmentedchart.component.StockAllocationItem
import id.timtam.segmentedchart.data.OtherStockAllocationItemUIState
import id.timtam.segmentedchart.data.StockAllocationItemUIState
import id.timtam.segmentedchart.extension.fromHex

@Composable
fun OtherStockAllocationLayout(
    modifier: Modifier = Modifier,
    otherStock: OtherStockAllocationItemUIState,
) {
    var isExpanded by rememberSaveable {
        mutableStateOf(false)
    }
    if (otherStock.isNotEmpty()) {
        Column(
            modifier =
                modifier
                    .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            StockAllocationItem(
                item = otherStock.totalOther,
                showProgressBar = true,
            )

            if (isExpanded) {
                OtherStockAllocationItem(stocks = otherStock.stocks)
            }

            Image(
                painter =
                    rememberVectorPainter(
                        ImageVector.vectorResource(R.drawable.ic_launcher_background),
                    ),
                contentDescription = null,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(16.dp)
                        .align(Alignment.CenterHorizontally)
                        .clickable {
                            isExpanded = !isExpanded
                        },
            )
        }
    }
}

@Composable
private fun OtherStockAllocationItem(stocks: List<StockAllocationItemUIState>) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .border(
                    width = 1.dp,
                    color = Color.fromHex("#2E2E2E"),
                    shape =
                        RoundedCornerShape(
                            size = 4.dp,
                        ),
                ).padding(horizontal = 12.dp),
    ) {
        stocks.forEach { stock ->
            key(stock.symbol) {
                StockAllocationItem(
                    item = stock,
                    showProgressBar = false,
                )
            }
        }
    }
}
