package com.example.myapplication.presentation.screens.dashboard

sealed class DashBoardEvent {
    data class Navigation(val router : String) : DashBoardEvent()
    object OpenDrawer : DashBoardEvent()
    data class SetCurrentPage(val index : Int) : DashBoardEvent()
    object GetAllCategory : DashBoardEvent()
}

