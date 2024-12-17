package id.timtam.segmentedchart.data

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D

data class StockAllocationChartUIState(
    val value: Int,
    val color: String,
    val valueAnimation: AllocationAnimationUIState = AllocationAnimationUIState(),
)

data class AllocationAnimationUIState(
    val animate: Boolean = false,
    val animation: Animatable<Float, AnimationVector1D> = Animatable(0f),
)
