package com.example.myapplication.presentation.navigations

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.presentation.screens.dashboard.nav.DashBoardNav
import com.example.myapplication.presentation.screens.onboard.OnboardingViewModel
import com.example.myapplication.presentation.screens.onboard.language.Language
import com.example.myapplication.presentation.screens.onboard.introduce.SlideIntroduce

lateinit var navController : NavHostController

@Composable
fun Navigation(){
    navController = rememberNavController()

   val viewModel : OnboardingViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = viewModel.routerDes.value){
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