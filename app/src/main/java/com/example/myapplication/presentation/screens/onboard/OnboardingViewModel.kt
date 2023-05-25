package com.example.myapplication.presentation.screens.onboard

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myapplication.R
import com.example.myapplication.domain.model.Display
import com.example.myapplication.presentation.navigations.navController
import com.example.myapplication.utils.Const
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor() : ViewModel() {
    private val _languageState = mutableStateOf(Const.getListLanguage().first { it.isChecked })
    val languageState: State<Display> = _languageState

    fun onEvent(event: OnboardEvent) {
        when (event) {
            is OnboardEvent.ChoiceLanguage -> _languageState.value = event.display
            is OnboardEvent.Navigation -> navController.navigate(event.router)
            else -> {}
        }
    }

    fun getIconChoiceLanguage(iconEnd: Int?, title: Int?) = iconEnd
        ?: if (languageState.value.title == title) R.drawable.ic_radio_checked else R.drawable.ic_radio
}