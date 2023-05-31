package com.example.myapplication.presentation.screens.dashboard

sealed class DashBoardEvent {
    data class Navigation(val router : String) : DashBoardEvent()
    object OpenDrawer : DashBoardEvent()
    data class SetCurrentPage(val index : Int) : DashBoardEvent()
    object GetAllCategory : DashBoardEvent()
    object OnClickBack : DashBoardEvent()
    object OnClickEnd1 : DashBoardEvent()
    object OnClickEnd2 : DashBoardEvent()
    object OnClickEnd3 : DashBoardEvent()
}

