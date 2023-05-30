package com.example.myapplication.presentation.screens.dashboard.nav

import android.annotation.SuppressLint
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.presentation.screens.dashboard.DashBoardViewModel
import com.example.myapplication.component.AppbarDashBoard
import com.example.myapplication.presentation.screens.dashboard.DashBoardEvent
import com.example.myapplication.presentation.screens.dashboard.nav.widgets.BottomBarNav
import com.example.myapplication.presentation.screens.dashboard.nav.widgets.DrawerContent
import com.example.myapplication.presentation.screens.dashboard.nav.widgets.Nav

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
internal fun DashBoardNav(viewModel: DashBoardViewModel = hiltViewModel()) {
    viewModel.navController = rememberNavController()
    viewModel.scaffoldState = rememberScaffoldState()
    viewModel.coroutine = rememberCoroutineScope()



    Scaffold(
        backgroundColor = colorResource(id = R.color.bg),
        scaffoldState = viewModel.scaffoldState,
        topBar = {
            AppbarDashBoard()
        },
        bottomBar = {
            BottomBarNav()
        },
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = colorResource(id = R.color.bottom_nav_color),
                onClick = { }) {

            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        drawerContent = {
            DrawerContent()
        }) {
        Nav()
    }
}


