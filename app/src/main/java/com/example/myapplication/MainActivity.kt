package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.navigations.Navigation
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.internal.GeneratedComponent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    var keepSplashScreen = true

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().setKeepOnScreenCondition {
            keepSplashScreen
        }

        lifecycleScope.launch {
            delay(2000)
            keepSplashScreen = false
        }

        setTheme(R.style.Theme_MyApplication)
        super.onCreate(savedInstanceState)

        setContent {
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

                    Navigation()
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun GreetingPreview() {
//    MyApplicationTheme() {
//        // A surface container using the 'background' color from the theme
//        Surface(modifier = Modifier.fillMaxSize()) {
//            val systemUiController = rememberSystemUiController()
//            val color = colorResource(id = R.color.status_bar_color)
//            SideEffect {
//                systemUiController.setStatusBarColor(
//                    color = color,
//                )
//            }
//
//            Navigation()
//        }
//    }
//}