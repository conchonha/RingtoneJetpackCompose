package com.example.myapplication.presentation.screens.dashboard.nav

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.R
import com.example.myapplication.presentation.screens.dashboard.DashBoardEvent
import com.example.myapplication.presentation.screens.dashboard.DashBoardViewModel
import com.example.myapplication.utils.extension.bottomBorder
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
internal fun DashBoardNav(viewModel: DashBoardViewModel = hiltViewModel()) {
    val scaffoldState = rememberScaffoldState()
    val coroutine = rememberCoroutineScope()

    LaunchedEffect(
        key1 = scaffoldState,
        scaffoldState.drawerState,
        scaffoldState.snackbarHostState,
        block = {
            viewModel.onEvent(DashBoardEvent.InitStateDashBoar(scaffoldState))
        })

    Scaffold(backgroundColor = colorResource(id = R.color.bg),
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(backgroundColor = colorResource(id = R.color.bg),
                modifier = Modifier
                    .fillMaxWidth()
                    .bottomBorder(0.3.dp, colorResource(id = R.color.bg1)),
                title = { Text(text = ("SangTb")) },
                actions = {
                    IconButton(onClick = {
                        coroutine.launch {
                            viewModel.onEvent(DashBoardEvent.OpenDrawer)
                        }
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_menu),
                            contentDescription = "Icon Menu",
                            tint = colorResource(id = R.color.bg1)
                        )
                    }
                    IconButton(onClick = {
                        coroutine.launch {
                            viewModel.onEvent(DashBoardEvent.OpenDrawer)
                        }
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_menu),
                            contentDescription = "Icon Menu",
                            tint = colorResource(id = R.color.bg1)
                        )
                    }

                    IconButton(onClick = {
                        coroutine.launch {
                            viewModel.onEvent(DashBoardEvent.OpenDrawer)
                        }
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_menu),
                            contentDescription = "Icon Menu",
                            tint = colorResource(id = R.color.bg1)
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {
                        coroutine.launch {
                            viewModel.onEvent(DashBoardEvent.OpenDrawer)
                        }
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_menu),
                            contentDescription = "Icon Menu",
                            tint = colorResource(id = R.color.bg1)
                        )
                    }
                })
        },
        bottomBar = {
            BottomAppBar(cutoutShape = CircleShape) {

            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { }) {

            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        drawerContent = {

        }) {

    }
}


