package com.example.myapplication.presentation.screens.dashboard

import android.annotation.SuppressLint
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.myapplication.data.data_source.ApiService
import com.example.myapplication.data.data_source.config.ResponseAPI
import com.example.myapplication.domain.model.CategoryResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashBoardViewModel @Inject constructor(public val apiService: ApiService) : ViewModel() {
    lateinit var scaffoldState: ScaffoldState
    lateinit var coroutine: CoroutineScope

    lateinit var navController: NavHostController
    private val _currentPage = mutableIntStateOf(0)
    val currentPage: State<Int> = _currentPage

    @SuppressLint("AutoboxingStateValueProperty")
    fun onEvent(event: DashBoardEvent) {
        coroutine.launch {
            when (event) {
                is DashBoardEvent.OpenDrawer -> scaffoldState.drawerState.open()
                is DashBoardEvent.SetCurrentPage -> _currentPage.value = event.index
                is DashBoardEvent.Navigation -> navController.navigate(event.router)
                is DashBoardEvent.GetAllCategory -> apiService.getAllCategory()
                else -> return@launch
            }
        }
    }
}