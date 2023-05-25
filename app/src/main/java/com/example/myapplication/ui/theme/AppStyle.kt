package com.example.myapplication.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapplication.R

object AppStyle {
    @Composable
    fun HeaderStyle() = MaterialTheme.typography.headlineLarge.copy(
        color = colorResource(
            id = R.color.title_color
        ),
        fontWeight = FontWeight.Bold
    )

    @Composable
    fun titleStyle() = MaterialTheme.typography.titleLarge.copy(
        color = colorResource(
            id = R.color.title_color
        )
    )

    @Composable
    fun titleStyleBold() = MaterialTheme.typography.titleLarge.copy(
        color = colorResource(
            id = R.color.title_color
        ),
        fontWeight = FontWeight.Bold
    )

    @Composable
    fun bodyStyle() = MaterialTheme.typography.bodyLarge.copy(
        color = colorResource(
            id = R.color.body_color
        )
    )

    @Composable
    fun bodyStyleNoTheme() = MaterialTheme.typography.bodyLarge.copy(
        color = colorResource(
            id = R.color.black
        ),
        fontWeight = FontWeight.Bold
    )

    @Composable
    fun smallType() = MaterialTheme.typography.labelSmall.copy(
        color = colorResource(
            id = R.color.body_color
        )
    )
}