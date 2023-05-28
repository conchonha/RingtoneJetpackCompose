package com.example.myapplication.presentation.screens.dashboard.nav.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.R
import com.example.myapplication.presentation.screens.dashboard.DashBoardEvent
import com.example.myapplication.presentation.screens.dashboard.DashBoardViewModel
import com.example.myapplication.utils.Const

@Composable
fun BottomBarNav(viewModel: DashBoardViewModel = hiltViewModel()) {

    BottomAppBar(
        cutoutShape = CircleShape,
        backgroundColor = colorResource(id = R.color.bottom_nav_color)
    ) {
        Const.getNavItems().forEachIndexed { index, dashBoardNav ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(id = dashBoardNav.icon),
                        contentDescription = dashBoardNav.label
                    )
                },
                label = {
                    Text(
                        dashBoardNav.label,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                onClick = {
                    viewModel.apply {
                        onEvent(DashBoardEvent.SetCurrentPage(index))
                        onEvent(DashBoardEvent.Navigation(dashBoardNav.router))
                    }
                },
                selected = index == viewModel.currentPage.value,
                selectedContentColor = colorResource(id = R.color.tab_color),
                unselectedContentColor = colorResource(id = R.color.tab_color_unselected),
                alwaysShowLabel = false,
            )
        }
    }
}