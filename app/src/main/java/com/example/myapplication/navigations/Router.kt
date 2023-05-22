package com.example.myapplication.navigations

sealed class Router(val router: String) {
    object Slider : Router("splash_screen")
}