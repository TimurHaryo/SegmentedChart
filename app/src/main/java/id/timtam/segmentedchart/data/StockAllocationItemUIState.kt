package id.timtam.segmentedchart.data

data class StockAllocationItemUIState(
    val symbol: String = "",
    val value: String = "",
    val percentageText: String = "",
    val percentage: Float = 0f,
    val color: String = ""
)
