package com.example.myapplication.presentation.screens.dashboard.nav.widgets

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.presentation.navigations.Router
import com.example.myapplication.presentation.screens.dashboard.DashBoardViewModel
import com.example.myapplication.presentation.screens.dashboard.call.Call
import com.example.myapplication.presentation.screens.dashboard.live_wallpaper.LiveWallPaper
import com.example.myapplication.presentation.screens.dashboard.ringtone.Ringtone
import com.example.myapplication.presentation.screens.dashboard.wallpaper.WallPaper

@Composable
fun Nav(viewModel: DashBoardViewModel = hiltViewModel()) {

    NavHost(
        navController = viewModel.navController,
        startDestination = Router.Ringtone.router
    ) {
        composable(Router.Ringtone.router) {
            Ringtone()
        }

        composable(Router.Wallpaper.router){
            WallPaper()
        }

        composable(Router.LiveWallPaper.router){
            LiveWallPaper()
        }

        composable(Router.Call.router){
            Call()
        }
    }
}