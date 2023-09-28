package com.example.myapplication.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.presentation.navigations.Navigation
import com.example.myapplication.presentation.screens.dashboard.DashBoardEvent
import com.example.myapplication.presentation.screens.dashboard.DashBoardViewModel
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.utils.ImageLoaderUtils
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    var keepSplashScreen = true
    private val dashBoardViewModel by viewModels<DashBoardViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().setKeepOnScreenCondition {
            keepSplashScreen
        }

        setTheme(R.style.Theme_MyApplication)
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            dashBoardViewModel.onEvent(DashBoardEvent.GetAllCategory)
            delay(10000)
            keepSplashScreen = false
        }

        setContent {
            dashBoardViewModel.apply {
                coroutine = rememberCoroutineScope()
                navController = rememberNavController()
                scaffoldState = rememberScaffoldState()
            }

            LoadImageUrl()

            MyApplicationTheme() {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize()) {
                    val systemUiController = rememberSystemUiController()
                    val color = colorResource(id = R.color.status_bar_color)

                    /**  SideEffect
                    Sử dụng để thực hiện các tác vụ không liên quan đến việc xây dựng giao diện,
                    được gọi mỗi khi compose được xây dựng lại, thường dùng getApi ... ->
                    liên quan đến UI thì nên dùng LaunchedEffect hoặc DisposableEffect
                     */
                    SideEffect {
                        systemUiController.setStatusBarColor(
                            color = color,
                        )
                    }

                    Navigation(dashBoardViewModel)
                }
            }
        }
    }

    @Composable
    private fun LoadImageUrl(){
        dashBoardViewModel.tabState.value.datas.forEach {
            ImageLoaderUtils.PreloadUrlImage(url = it.image.toString())
        }
    }
}