package id.timtam.segmentedchart.data

data class OtherStockAllocationItemUIState(
    val totalOther: StockAllocationItemUIState,
    val stocks: List<StockAllocationItemUIState>,
) {
    fun isNotEmpty(): Boolean = stocks.isNotEmpty()
}
