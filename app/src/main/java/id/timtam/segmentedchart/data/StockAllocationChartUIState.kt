package id.timtam.segmentedchart.data

data class StockAllocationChartUIState(
    val value: Int,
    val color: String,
    val valueAnimation: AllocationAnimationUIState = AllocationAnimationUIState(),
)

data class AllocationAnimationUIState(
    val animate: Boolean = false,
    val value: Float = 0f,
)
