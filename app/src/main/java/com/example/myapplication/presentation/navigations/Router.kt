package com.example.myapplication.presentation.navigations

sealed class Router(val router: String) {
    object Slider : Router("slider")
    object Language : Router("language_screen")
    object DashBoard : Router("dash_board_nav")
    object Ringtone : Router("ringtone")
    object Wallpaper : Router("wallpaper")
    object LiveWallPaper : Router("liveWallPaper")
    object Call : Router("call")
}
