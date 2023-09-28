package com.example.myapplication.presentation.screens.dashboard

import android.annotation.SuppressLint
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.example.myapplication.R
import com.example.myapplication.component.AppbarDashBoard
import com.example.myapplication.presentation.screens.dashboard.widgets.BottomBarNav
import com.example.myapplication.presentation.screens.dashboard.widgets.DrawerContent
import com.example.myapplication.presentation.screens.dashboard.widgets.Nav

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
internal fun DashBoardNav(viewModel: DashBoardViewModel) {

    Scaffold(
        backgroundColor = colorResource(id = R.color.bg),
        scaffoldState = viewModel.scaffoldState,
        topBar = {
            AppbarDashBoard()
        },
        bottomBar = {
            BottomBarNav(viewModel)
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
        Nav(viewModel)
    }
}


