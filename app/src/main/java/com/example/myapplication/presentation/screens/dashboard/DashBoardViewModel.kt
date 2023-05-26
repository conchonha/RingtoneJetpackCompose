package com.example.myapplication.presentation.screens.dashboard

import android.app.Application
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myapplication.R
import com.example.myapplication.app.MyApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DashBoardViewModel @Inject constructor() : ViewModel() {
    lateinit var scaffoldState: ScaffoldState
    lateinit var coroutine: CoroutineScope

    lateinit var navController: NavHostController
    var navBackStackEntry: NavBackStackEntry? = null
    val currentRouter = navBackStackEntry?.destination?.route

    fun onEvent(event: DashBoardEvent) {
        coroutine.launch {
            when (event) {
                is DashBoardEvent.OpenDrawer -> scaffoldState.drawerState.open()
                is DashBoardEvent.InitStateDashBoar -> scaffoldState = event.scaffoldState
                else -> return@launch
            }
        }
    }
}