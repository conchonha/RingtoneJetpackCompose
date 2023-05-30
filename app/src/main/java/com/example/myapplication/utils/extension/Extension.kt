package com.example.myapplication.utils.extension

import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import com.example.myapplication.data.data_source.config.ResponseAPI
import com.example.myapplication.domain.model.CategoryResponse
import com.example.myapplication.domain.model.SortedModel
import com.example.myapplication.utils.storted.SortedProperty
import com.example.myapplication.utils.storted.SortedType
import kotlinx.coroutines.flow.map


fun Modifier.bottomBorder(strokeWidth: Dp, color: Color) = composed(factory = {
    val density = LocalDensity.current
    val strokeWidthPx = density.run { strokeWidth.toPx() }

    Modifier.drawBehind {
        val width = size.width
        val height = size.height - strokeWidthPx / 2

        drawLine(
            color = color,
            start = Offset(x = 0f, y = size.height),
            end = Offset(x = width, y = size.height),
            strokeWidth = strokeWidthPx
        )
    }
})

fun Modifier.topBorder(strokeWidth: Dp, color: Color) = composed(factory = {
    val density = LocalDensity.current
    val strokeWidthPx = density.run { strokeWidth.toPx() }

    Modifier.drawBehind {
        val width = size.width

        drawLine(
            color = color,
            start = Offset(x = 0f, y = 0f),
            end = Offset(x = width, y = 0f),
            strokeWidth = strokeWidthPx
        )
    }
})


fun List<SortedModel>.sored(sortedProperty: SortedProperty) : List<SortedModel>{
    val data: List<SortedModel> = when (sortedProperty.sortedType) {
        is SortedType.Ascending -> {
            when (sortedProperty) {
                is SortedProperty.Title -> this.sortedBy {
                    it.titleSort.lowercase()
                }

                is SortedProperty.Id -> this.sortedBy { it.idSort }
                is SortedProperty.Size -> this.sortedBy { it.sizeSort }
                else -> this.shuffled()
            }
        }

        is SortedType.Descending -> {
            when (sortedProperty) {
                is SortedProperty.Title -> this.sortedByDescending {
                    it.titleSort.lowercase()
                }

                is SortedProperty.Id -> this.sortedByDescending { it.idSort }
                is SortedProperty.Size -> this.sortedByDescending { it.sizeSort }
                else -> this.shuffled()
            }
        }

        else -> this.shuffled()
    }
    return data
}

// val a =  (2 == 3) or1 10 or2 20
infix fun <T> Boolean.or1(value: T): ValueWrapper<T> = ValueWrapper(this, value)
infix fun Boolean.orNull(value: Any): Any? = if (this) value else null
infix fun <T> Boolean?.orNull(value: T): T? {
    return if (this == true) {
        value
    } else null
}

class ValueWrapper<T>(private val condition: Boolean, private val value: T) {
    infix fun or2(value: T): T = if (condition) this.value else value
}

