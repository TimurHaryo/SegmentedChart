package id.timtam.segmentedchart.extension

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import kotlin.math.PI
import kotlin.math.asin
import kotlin.math.cos
import kotlin.math.sin

fun Color.Companion.fromHex(hex: String): Color {
    if (hex.isEmpty()) {
        return Transparent
    }

    return if (hex.startsWith("#")) {
        Color(android.graphics.Color.parseColor(hex))
    } else {
        Color(android.graphics.Color.parseColor("#$hex"))
    }
}

fun Path.addRoundedEdgeBothSides(
    center: Offset,
    startAngleDegrees: Float,
    sweepAngleDegrees: Float,
    innerRadius: Float,
    outerRadius: Float,
    cornerRadius: Float,
) {
    val endAngleDegrees = startAngleDegrees + sweepAngleDegrees.toDouble()
    val innerRadiusShift = innerRadius + cornerRadius.toDouble()
    val innerAngleShift = asin(cornerRadius / innerRadiusShift) * 180 / PI
    val outerRadiusShift = outerRadius - cornerRadius.toDouble()
    val outerAngleShift = asin(cornerRadius / outerRadiusShift) * 180 / PI
    arcTo(
        rect =
            Rect(
                center =
                    Offset(
                        x = (center.x + innerRadiusShift * cos((startAngleDegrees + innerAngleShift) * PI / 180)).toFloat(),
                        y = (center.y + innerRadiusShift * sin((startAngleDegrees + innerAngleShift) * PI / 180)).toFloat(),
                    ),
                radius = cornerRadius,
            ),
        startAngleDegrees = startAngleDegrees - 90,
        sweepAngleDegrees = (innerAngleShift - 90).toFloat(),
        forceMoveTo = true,
    )
    arcTo(
        rect = Rect(center = center, radius = innerRadius),
        startAngleDegrees = (startAngleDegrees + innerAngleShift).toFloat(),
        sweepAngleDegrees = (sweepAngleDegrees - innerAngleShift).toFloat(),
        forceMoveTo = false,
    )
    arcTo(
        rect =
            Rect(
                center =
                    Offset(
                        x = (center.x + innerRadiusShift * cos((endAngleDegrees - innerAngleShift) * PI / 180)).toFloat(),
                        y = (center.y + innerRadiusShift * sin((endAngleDegrees - innerAngleShift) * PI / 180)).toFloat(),
                    ),
                radius = cornerRadius,
            ),
        startAngleDegrees = (endAngleDegrees - innerAngleShift + 180).toFloat(),
        sweepAngleDegrees = (innerAngleShift - 90).toFloat(),
        forceMoveTo = false,
    )
    arcTo(
        rect =
            Rect(
                center =
                    Offset(
                        x = (center.x + outerRadiusShift * cos((endAngleDegrees - outerAngleShift) * PI / 180)).toFloat(),
                        y = (center.y + outerRadiusShift * sin((endAngleDegrees - outerAngleShift) * PI / 180)).toFloat(),
                    ),
                radius = cornerRadius,
            ),
        startAngleDegrees = (endAngleDegrees + 90).toFloat(),
        sweepAngleDegrees = -(outerAngleShift + 90).toFloat(),
        forceMoveTo = false,
    )
    arcTo(
        rect = Rect(center = center, radius = outerRadius),
        startAngleDegrees = (endAngleDegrees).toFloat(),
        sweepAngleDegrees = -(sweepAngleDegrees - 2 * outerAngleShift).toFloat(),
        forceMoveTo = false,
    )
    arcTo(
        rect =
            Rect(
                center =
                    Offset(
                        x = (center.x + outerRadiusShift * cos((startAngleDegrees + outerAngleShift) * PI / 180)).toFloat(),
                        y = (center.y + outerRadiusShift * sin((startAngleDegrees + outerAngleShift) * PI / 180)).toFloat(),
                    ),
                radius = cornerRadius,
            ),
        startAngleDegrees = (startAngleDegrees + outerAngleShift).toFloat(),
        sweepAngleDegrees = -(outerAngleShift + 90).toFloat(),
        forceMoveTo = false,
    )
    close()
}

fun Path.addRoundedEdgeStartSide(
    center: Offset,
    startAngleDegrees: Float,
    sweepAngleDegrees: Float,
    innerRadius: Float,
    outerRadius: Float,
    cornerRadius: Float,
) {
    val endAngleDegrees = startAngleDegrees + sweepAngleDegrees.toDouble()
    val innerRadiusShift = innerRadius + cornerRadius.toDouble()
    val innerAngleShift = asin(cornerRadius / innerRadiusShift) * 180 / PI
    val outerRadiusShift = outerRadius - cornerRadius.toDouble()
    val outerAngleShift = asin(cornerRadius / outerRadiusShift) * 180 / PI
    arcTo(
        rect =
            Rect(
                center =
                    Offset(
                        x = (center.x + innerRadiusShift * cos((startAngleDegrees + innerAngleShift) * PI / 180)).toFloat(),
                        y = (center.y + innerRadiusShift * sin((startAngleDegrees + innerAngleShift) * PI / 180)).toFloat(),
                    ),
                radius = cornerRadius,
            ),
        startAngleDegrees = startAngleDegrees - 90,
        sweepAngleDegrees = (innerAngleShift - 90).toFloat(),
        forceMoveTo = true,
    )

    arcTo(
        rect = Rect(center = center, radius = innerRadius),
        startAngleDegrees = (startAngleDegrees + innerAngleShift).toFloat(),
        sweepAngleDegrees = (sweepAngleDegrees - innerAngleShift).toFloat(),
        forceMoveTo = false,
    )

    arcTo(
        rect = Rect(center = center, radius = outerRadius),
        startAngleDegrees = (endAngleDegrees).toFloat(),
        sweepAngleDegrees = -(sweepAngleDegrees - 2 * outerAngleShift).toFloat(),
        forceMoveTo = false,
    )
    arcTo(
        rect =
            Rect(
                center =
                    Offset(
                        x = (center.x + outerRadiusShift * cos((startAngleDegrees + outerAngleShift) * PI / 180)).toFloat(),
                        y = (center.y + outerRadiusShift * sin((startAngleDegrees + outerAngleShift) * PI / 180)).toFloat(),
                    ),
                radius = cornerRadius,
            ),
        startAngleDegrees = (startAngleDegrees + outerAngleShift).toFloat(),
        sweepAngleDegrees = -(outerAngleShift + 90).toFloat(),
        forceMoveTo = false,
    )
    close()
}

fun Path.addRoundedEdgeEndSide(
    center: Offset,
    startAngleDegrees: Float,
    sweepAngleDegrees: Float,
    innerRadius: Float,
    outerRadius: Float,
    cornerRadius: Float,
) {
    val endAngleDegrees = startAngleDegrees + sweepAngleDegrees.toDouble()
    val innerRadiusShift = innerRadius + cornerRadius.toDouble()
    val innerAngleShift = asin(cornerRadius / innerRadiusShift) * 180 / PI
    val outerRadiusShift = outerRadius - cornerRadius.toDouble()
    val outerAngleShift = asin(cornerRadius / outerRadiusShift) * 180 / PI
    arcTo(
        rect =
            Rect(
                center =
                    Offset(
                        x = (center.x + innerRadiusShift * cos((startAngleDegrees) * PI / 180)).toFloat(),
                        y = (center.y + innerRadiusShift * sin((startAngleDegrees) * PI / 180)).toFloat(),
                    ),
                radius = 0.1f,
            ),
        startAngleDegrees = startAngleDegrees,
        sweepAngleDegrees = 90f,
        forceMoveTo = true,
    )
    arcTo(
        rect = Rect(center = center, radius = innerRadius),
        startAngleDegrees = startAngleDegrees,
        sweepAngleDegrees = (sweepAngleDegrees - 2 * innerAngleShift).toFloat(),
        forceMoveTo = false,
    )
    arcTo(
        rect =
            Rect(
                center =
                    Offset(
                        x = (center.x + innerRadiusShift * cos((endAngleDegrees - innerAngleShift) * PI / 180)).toFloat(),
                        y = (center.y + innerRadiusShift * sin((endAngleDegrees - innerAngleShift) * PI / 180)).toFloat(),
                    ),
                radius = cornerRadius,
            ),
        startAngleDegrees = (endAngleDegrees - innerAngleShift + 180).toFloat(),
        sweepAngleDegrees = (innerAngleShift - 90).toFloat(),
        forceMoveTo = false,
    )
    arcTo(
        rect =
            Rect(
                center =
                    Offset(
                        x = (center.x + outerRadiusShift * cos((endAngleDegrees - outerAngleShift) * PI / 180)).toFloat(),
                        y = (center.y + outerRadiusShift * sin((endAngleDegrees - outerAngleShift) * PI / 180)).toFloat(),
                    ),
                radius = cornerRadius,
            ),
        startAngleDegrees = (endAngleDegrees + 90).toFloat(),
        sweepAngleDegrees = -(outerAngleShift + 90).toFloat(),
        forceMoveTo = false,
    )
    arcTo(
        rect = Rect(center = center, radius = outerRadius),
        startAngleDegrees = (endAngleDegrees - innerAngleShift).toFloat(),
        sweepAngleDegrees = -(sweepAngleDegrees - innerAngleShift).toFloat(),
        forceMoveTo = false,
    )

    close()
}
