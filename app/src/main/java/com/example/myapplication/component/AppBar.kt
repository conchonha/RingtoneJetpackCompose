package com.example.myapplication.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.R
import com.example.myapplication.presentation.screens.dashboard.DashBoardEvent
import com.example.myapplication.presentation.screens.dashboard.DashBoardViewModel
import com.example.myapplication.utils.extension.bottomBorder

@Composable
fun AppbarDashBoard(
    modifier: Modifier = Modifier,
    viewModel: DashBoardViewModel = hiltViewModel()
) {
    TopAppBar(
        backgroundColor = colorResource(id = R.color.bg),
        modifier = modifier
            .fillMaxWidth()
            .bottomBorder(0.3.dp, colorResource(id = R.color.bg1)),
        title = {
            Text(text = ("SangTb"))
        },
        actions = {
            IconButton(
                onClick = {
                    viewModel.onEvent(DashBoardEvent.OpenDrawer)
                }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_menu),
                    contentDescription = "Icon Menu",
                    tint = colorResource(id = R.color.bg1)
                )
            }
            IconButton(
                onClick = {
                    viewModel.onEvent(DashBoardEvent.OpenDrawer)
                }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_menu),
                    contentDescription = "Icon Menu",
                    tint = colorResource(id = R.color.bg1)
                )
            }

            IconButton(
                onClick = {
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
            IconButton(
                onClick = {
                    viewModel.onEvent(DashBoardEvent.OpenDrawer)
                }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_menu),
                    contentDescription = "Icon Menu",
                    tint = colorResource(id = R.color.bg1)
                )
            }
        })
}


