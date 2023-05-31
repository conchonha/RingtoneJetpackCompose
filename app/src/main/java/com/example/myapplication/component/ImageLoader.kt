package com.example.myapplication.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun ImageWithProgress(
    modifier: Modifier = Modifier,
    url: String,
    size: Dp,
    shape: Shape = CircleShape
) {
    val progressVisible = remember { mutableStateOf(true) }

    Box(
        modifier = modifier
            .size(size)
            .clip(shape)
            .background(Color.Red)
    ) {
        Image(
            painter = rememberAsyncImagePainter(url,
                onLoading = { progressVisible.value = true },
                onError = { progressVisible.value = false },
                onSuccess = { progressVisible.value = false }),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(size)
        )

        if (progressVisible.value) {
            LinearProgressIndicator(modifier = Modifier.fillMaxSize())
        }
    }
}