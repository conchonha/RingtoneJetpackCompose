package com.example.myapplication.presentation.screens.dashboard

import android.annotation.SuppressLint
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.domain.model.CategoryResponse
import com.example.myapplication.domain.use_case.RingtoneUserCase
import com.example.myapplication.presentation.screens.dashboard.DashBoardViewModel.Companion.EMPTY_ICON_DEFAULT
import com.example.myapplication.utils.storted.SortedProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

typealias CallBackDefault = (() -> Unit)?

@HiltViewModel
class DashBoardViewModel @Inject constructor(private val ringtoneUserCase: RingtoneUserCase) :
    ViewModel() {
    lateinit var scaffoldState: ScaffoldState
    var coroutine: CoroutineScope? = null
    lateinit var navController: NavHostController

    private val _currentPage = mutableIntStateOf(0)
    val currentPage: State<Int> = _currentPage

    private val _tabState = mutableStateOf(TabState())
    val tabState: State<TabState> = _tabState

    private val _appbarState = mutableStateOf(AppBarState())
    val appbarState: State<AppBarState> = _appbarState

    @SuppressLint("AutoboxingStateValueProperty")
    fun onEvent(event: DashBoardEvent) {
        (coroutine ?: viewModelScope).launch {
            when (event) {
                is DashBoardEvent.OpenDrawer -> scaffoldState.drawerState.open()
                is DashBoardEvent.SetCurrentPage -> _currentPage.value = event.index
                is DashBoardEvent.Navigation -> navController.navigate(event.router)
                is DashBoardEvent.GetAllCategory -> ringtoneUserCase.getAllCategory(SortedProperty.Normal)
                    .onEach {
                        _tabState.value = tabState.value.copy(datas = it)
                    }.flowOn(Dispatchers.Main)
                    .launchIn(viewModelScope)

                else -> {}
            }
        }
    }

    companion object {
        const val EMPTY_ICON_DEFAULT = -1
    }
}

data class TabState(
    val datas: List<CategoryResponse> = emptyList(),
    val sortedProperty: SortedProperty = SortedProperty.Normal,
    val isLinearLayout: Boolean = false
)

data class AppBarState(
    val title: String = "",
    val isVisibleIconBack: Boolean = true,
    val isVisibleIconEnd1: Boolean = false,
    val isVisibleIconEnd2: Boolean = false,
    val isVisibleIconEnd3: Boolean = false,
    val srcIconEnd1: Int = R.drawable.ic_grird,
    val srcIconEnd2: Int = R.drawable.ic_search,
    val srcIconEnd3: Int = EMPTY_ICON_DEFAULT,
    val srcIconBack: Int = R.drawable.ic_menu
)