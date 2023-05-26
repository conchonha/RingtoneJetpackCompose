package com.example.myapplication.presentation.screens.onboard

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myapplication.R
import com.example.myapplication.domain.model.Display
import com.example.myapplication.presentation.navigations.Router
import com.example.myapplication.presentation.navigations.navController
import com.example.myapplication.utils.Const
import com.example.myapplication.utils.SharePrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor() : ViewModel() {
    private val _sharePrefs by lazy { SharePrefs.getInstance() }
    private val _languageState = mutableStateOf(Const.getListLanguage().first { it.isChecked })
    val languageState: State<Display> = _languageState

    private val _routerDestination = mutableStateOf(Router.Language.router)
    val routerDes : State<String> = _routerDestination

    @OptIn(ExperimentalFoundationApi::class)
    lateinit var pagerState: PagerState

    init {
        _routerDestination.value = when{
            _sharePrefs.get<Boolean>(Const.KEY_FIRST_LAUNCH_INTRODUCE) == IS_FIRST_LAUNCH -> Router.DashBoard.router
            _sharePrefs.get<Boolean>(Const.KEY_FIRST_LAUNCH_LANGUAGE) == IS_FIRST_LAUNCH -> Router.Slider.router
            else -> Router.Language.router
        }
    }

    fun onEvent(event: OnboardEvent) {
        when (event) {
            is OnboardEvent.ChoiceLanguage -> _languageState.value = event.display
            is OnboardEvent.Navigation -> handleNavigateEvent(event.router)
            else -> return
        }
    }

    private fun handleNavigateEvent(router: String) {
        when (router) {
            Router.Slider.router -> _sharePrefs.put(Const.KEY_FIRST_LAUNCH_LANGUAGE, IS_FIRST_LAUNCH)
            Router.DashBoard.router -> _sharePrefs.put(Const.KEY_FIRST_LAUNCH_INTRODUCE, IS_FIRST_LAUNCH)
        }
        navController.navigate(router)
    }

    fun getIconChoiceLanguage(iconEnd: Int?, title: Int?) = iconEnd
        ?: if (languageState.value.title == title) R.drawable.ic_radio_checked else R.drawable.ic_radio

    companion object{
        private const val IS_FIRST_LAUNCH = true
    }
}