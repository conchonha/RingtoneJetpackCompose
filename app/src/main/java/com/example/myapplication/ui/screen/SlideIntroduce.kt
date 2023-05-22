package com.example.myapplication.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.myapplication.LoginScreen
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun SlideIntroduce(navController: NavController){

}

@Preview(showBackground = true)
@Composable
fun PreviewSplash() {
    MyApplicationTheme {
        LoginScreen()
    }
}