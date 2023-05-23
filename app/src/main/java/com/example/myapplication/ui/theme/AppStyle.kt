package com.example.myapplication.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.example.myapplication.R

object AppStyle{
    @Composable
    fun titleStyle() = MaterialTheme.typography.titleLarge.copy(
        color = colorResource(
            id = R.color.title_color
        )
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
}