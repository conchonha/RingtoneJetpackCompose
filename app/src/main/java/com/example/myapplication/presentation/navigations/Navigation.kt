package com.example.myapplication.presentation.navigations

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.presentation.screens.dashboard.DashBoardNav
import com.example.myapplication.presentation.screens.onboard.language.Language
import com.example.myapplication.presentation.screens.onboard.introduce.SlideIntroduce

lateinit var navController : NavHostController

@Composable
fun Navigation(){
    val _navController = rememberNavController()
    navController = _navController

    LaunchedEffect(key1 = _navController, block = {
        navController = _navController
    })

    NavHost(navController = navController, startDestination = Router.Language.router){
        composable(Router.Language.router){
            Language()
        }

        composable(Router.Slider.router){
            SlideIntroduce()
        }

        composable(Router.DashBoard.router){
            DashBoardNav()
        }
    }
}