package com.example.myapplication.presentation.screens.dashboard

import androidx.compose.material.ScaffoldState


sealed class DashBoardEvent {
    data class Navigation(val router : String) : DashBoardEvent()
    data class InitStateDashBoar(val scaffoldState: ScaffoldState) : DashBoardEvent()
    object OpenDrawer : DashBoardEvent()
}