package com.example.myapplication.presentation.screens.onboard

import com.example.myapplication.domain.model.Display


sealed class OnboardEvent {
    data class UpdateLanguage(val display: Display) : OnboardEvent()
    data class ChoiceLanguage(val display: Display) : OnboardEvent()
    data class Navigation(val router: String) : OnboardEvent()
}