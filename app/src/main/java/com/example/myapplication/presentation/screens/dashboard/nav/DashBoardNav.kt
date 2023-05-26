package com.example.myapplication.presentation.screens.dashboard.nav

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.presentation.navigations.Router
import com.example.myapplication.presentation.navigations.navController
import com.example.myapplication.presentation.screens.dashboard.DashBoardEvent
import com.example.myapplication.presentation.screens.dashboard.DashBoardViewModel
import com.example.myapplication.presentation.screens.onboard.OnboardingViewModel
import com.example.myapplication.presentation.screens.onboard.introduce.SlideIntroduce
import com.example.myapplication.presentation.screens.onboard.language.Language
import com.example.myapplication.utils.Const.getNavItems
import com.example.myapplication.utils.extension.bottomBorder
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
internal fun DashBoardNav() {
    val viewModel: DashBoardViewModel = hiltViewModel()
    viewModel.scaffoldState = rememberScaffoldState()
    viewModel.coroutine = rememberCoroutineScope()

    Scaffold(backgroundColor = colorResource(id = R.color.bg),
        scaffoldState = viewModel.scaffoldState,
        topBar = {
            TopAppBar(backgroundColor = colorResource(id = R.color.bg),
                modifier = Modifier
                    .fillMaxWidth()
                    .bottomBorder(0.3.dp, colorResource(id = R.color.bg1)),
                title = { Text(text = ("SangTb")) },
                actions = {
                    IconButton(onClick = {
                        viewModel.onEvent(DashBoardEvent.OpenDrawer)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_menu),
                            contentDescription = "Icon Menu",
                            tint = colorResource(id = R.color.bg1)
                        )
                    }
                    IconButton(onClick = {
                        viewModel.onEvent(DashBoardEvent.OpenDrawer)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_menu),
                            contentDescription = "Icon Menu",
                            tint = colorResource(id = R.color.bg1)
                        )
                    }

                    IconButton(onClick = {
                        viewModel.onEvent(DashBoardEvent.OpenDrawer)
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
                        viewModel.onEvent(DashBoardEvent.OpenDrawer)
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
            viewModel.navBackStackEntry = viewModel.navController.currentBackStackEntry

            BottomAppBar(cutoutShape = CircleShape) {
                getNavItems().forEach { pair ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                painterResource(id = pair.second),
                                contentDescription = pair.first
                            )
                        },
                        label = { Text(pair.first) },
                        selected = pair.first == viewModel.currentRouter,
                        onClick = {

                        })
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { }) {

            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        drawerContent = {
            viewModel.navController = rememberNavController()

            NavHost(navController = viewModel.navController, startDestination = Router.Language.router) {d
                composable(Router.Language.router) {
                    Language()
                }

                composable(Router.Slider.router) {
                    SlideIntroduce()
                }

                composable(Router.DashBoard.router) {
                    DashBoardNav()
                }
            }
        }) {

    }
}


