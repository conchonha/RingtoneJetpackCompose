package com.example.myapplication.presentation.screens.dashboard

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.myapplication.data.data_source.ApiService
import com.example.myapplication.data.data_source.config.ResponseAPI
import com.example.myapplication.domain.model.CategoryResponse
import com.example.myapplication.domain.use_case.RingtoneUserCase
import com.example.myapplication.utils.storted.SortedProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class DashBoardViewModel @Inject constructor(private val ringtoneUserCase: RingtoneUserCase) :
    ViewModel() {
    lateinit var scaffoldState: ScaffoldState
    lateinit var coroutine: CoroutineScope
    lateinit var navController: NavHostController

    private val _currentPage = mutableIntStateOf(0)
    val currentPage: State<Int> = _currentPage

    private val _categoryState = mutableStateOf(CategoryState())
    val categoryState: State<CategoryState> = _categoryState

    var list = MutableStateFlow<List<CategoryResponse>>(emptyList())
    val list1: StateFlow<List<CategoryResponse>> = list

    @SuppressLint("AutoboxingStateValueProperty")
    infix fun onEvent(event: DashBoardEvent) {
        coroutine.launch {
            when (event) {
                is DashBoardEvent.OpenDrawer -> scaffoldState.drawerState.open()
                is DashBoardEvent.SetCurrentPage -> _currentPage.value = event.index
                is DashBoardEvent.Navigation -> navController.navigate(event.router)
                is DashBoardEvent.GetAllCategory -> ringtoneUserCase.getAllCategory(SortedProperty.Normal)
                    .onEach {
                        _categoryState.value = CategoryState(it)
                        list.emit(it)
                        Log.d("AAAA", "onEvent: ${_categoryState.value.datas} ---- value:")
                    }.flowOn(Dispatchers.Main)
                    .launchIn(viewModelScope)
            }
        }
    }
}

data class CategoryState(
    val datas: List<CategoryResponse> = emptyList(),
    val sortedProperty: SortedProperty = SortedProperty.Normal,
    val isLinearLayout: Boolean = false
)