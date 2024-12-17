package id.timtam.segmentedchart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.timtam.segmentedchart.data.AllocationAnimationUIState
import id.timtam.segmentedchart.data.StockAllocationChartUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {
    private val _state = MutableStateFlow<Resource>(Resource.Loading)
    val state: StateFlow<Resource> get() = _state

    fun populateData() {
        viewModelScope.launch {
            _state.emit(Resource.Loading)
            var data = Data.stockData.sortedByDescending { it.value }

            repeat(10) {
                delay(2000)
                val (idx, newData) = data.randomize()
                data = newData
                _state.emit(Resource.Success(data, idx))
            }
        }
    }

    private suspend fun List<StockAllocationChartUIState>.randomize(): Pair<Int, List<StockAllocationChartUIState>> {
        val idx = indices.random()
        val randomSeed = listOf(5000, -5000).random()
        val total = (sumOf { it.value } + randomSeed).toFloat()
        return withContext(Dispatchers.Default) {
            idx to
                mapIndexed { index, stockPercentageChartUIState ->
                    if (index == idx) {
                        val newValue = stockPercentageChartUIState.value + randomSeed
                        stockPercentageChartUIState.copy(
                            value = newValue,
                            valueAnimation =
                                AllocationAnimationUIState(
                                    animate = true,
                                    value = 360f * (newValue / total),
                                ),
                        )
                    } else {
                        stockPercentageChartUIState.copy(
                            valueAnimation =
                                stockPercentageChartUIState.valueAnimation.copy(
                                    animate = false,
                                ),
                        )
                    }
                }.filter { it.value > 0 }.sortedByDescending { it.value }
        }
    }
}

sealed class Resource {
    data object Loading : Resource()

    data class Success(
        val data: List<StockAllocationChartUIState>,
        val changeOn: Int,
    ) : Resource()
}
