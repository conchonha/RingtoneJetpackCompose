package com.example.myapplication.utils

import android.R
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.core.graphics.drawable.toBitmap
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.size.Scale
import com.example.myapplication.app.MyApplication
import kotlinx.coroutines.launch

object ImageLoaderUtils {
    private val paintCache: MutableMap<String, ImageBitmap> = mutableMapOf()

    fun getPaint(url: String): ImageBitmap? {
        return paintCache[url]
    }

    @Composable
    fun LoadImage(
        modifier: Modifier = Modifier, url: String, size: Dp,
        shape: Shape = CircleShape
    ) {
        val bitmap = getPaint(url.trim())
        if (bitmap != null) {
            ImageWithBitMap(
                bitmap = bitmap,
                modifier = modifier,
                size = size,
                shape = shape,
            )
        } else {
            ImageWithProgress(
                modifier = modifier,
                url = url,
                size = size,
                shape = shape,
            )
        }
    }

    @Composable
    fun PreloadUrlImage(url: String) {
        Log.d("AAAA", "PreloadUrlImage: $url")
        if (getPaint(url) != null) return
        val coroutine = rememberCoroutineScope()
        var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }

        // Cấu hình các thiết lập cho ImageRequest
        val imagePainter = rememberAsyncImagePainter(
            ImageRequest.Builder // Thiết lập kiểu scale
                (MyApplication.application).data(data = url)
                .apply(block = fun ImageRequest.Builder.() {
                    // Cấu hình các thiết lập cho ImageRequest
                    scale(Scale.FILL) // Thiết lập kiểu scale
                    placeholder(R.drawable.ic_menu_gallery) // Hình ảnh placeholder
                    error(R.drawable.ic_menu_report_image) // Hình ảnh hiển thị khi có lỗi
                    fallback(R.drawable.ic_menu_help) // Hình ảnh dự phòng
                    memoryCachePolicy(CachePolicy.DISABLED) // Tắt cache trong bộ nhớ
                    diskCachePolicy(CachePolicy.DISABLED) // Bật cache trên ổ đĩa
                    allowHardware(false) // Không sử dụng hardware bitmap
                    listener { request, result ->
                        imageBitmap = result.drawable.toBitmap().asImageBitmap()
                    }
                }).build()
        )

        SideEffect {
            coroutine.launch {
                Log.d("SangTB", "PreloadUrlImage: coroutine start")
                imagePainter.imageLoader.execute(imagePainter.request)
            }
        }

        LaunchedEffect(imageBitmap) {
            if (imageBitmap != null) {
                Log.d("AAAAA", "PreloadUrlImage: imageBitmap: $imageBitmap")
                paintCache[url.trim()] = imageBitmap!!
            }
        }
    }

    @Composable
    fun ImageWithProgress(
        modifier: Modifier = Modifier,
        url: String,
        size: Dp,
        shape: Shape = CircleShape,
    ) {
        val progressVisible = remember { mutableStateOf(true) }

        Box(
            modifier = modifier
                .size(size)
                .clip(shape)
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

    @Composable
    fun ImageWithBitMap(
        modifier: Modifier = Modifier,
        bitmap: ImageBitmap,
        size: Dp,
        shape: Shape = CircleShape,
    ) {
        Box(
            modifier = modifier
                .size(size)
                .clip(shape)
        ) {
            Image(
                bitmap = bitmap,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(size)
            )
        }
    }

    fun removeBitmaps(list: List<String>){
        list.forEach {
            paintCache.remove(it.trim())
        }
    }

    fun removeBitMap(url : String){
        paintCache.remove(url.trim())
    }

    fun clear(){
        paintCache.clear()
    }
}
