package com.example.myapplication.presentation.screens.dashboard

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DashBoardViewModel @Inject constructor(

) : ViewModel() {
    lateinit var scaffoldState: ScaffoldState

    suspend fun onEvent(event: DashBoardEvent) {
        when (event) {
            is DashBoardEvent.OpenDrawer -> scaffoldState.drawerState.open()
            is DashBoardEvent.InitStateDashBoar -> scaffoldState = event.scaffoldState
            else -> return
        }
    }
}