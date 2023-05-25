package com.example.myapplication.presentation.navigations

sealed class Router(val router: String) {
    object Slider : Router("splash_screen")
    object Language : Router("language_screen")
    object DashBoard : Router("dash_board_nav")
}