package com.example.myapplication.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.screen.SlideIntroduce

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Router.Slider.router){
        composable(Router.Slider.router){
            SlideIntroduce(navController = navController)
        }
    }
}