package com.example.myapplication.navigations

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.screen.Language
import com.example.myapplication.ui.screen.SlideIntroduce
import com.example.myapplication.viewmodel.OnboardingViewModel

@Composable
fun Navigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Router.Slider.router){
        composable(Router.Slider.router){
            val viewModel : OnboardingViewModel = hiltViewModel()
            SlideIntroduce(navController)
        }
        composable(Router.Language.router){
            Language(navController)
        }
    }
}