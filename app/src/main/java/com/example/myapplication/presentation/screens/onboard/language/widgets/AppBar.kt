package com.example.myapplication.presentation.screens.onboard.language.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.R
import com.example.myapplication.presentation.navigations.Router
import com.example.myapplication.presentation.screens.onboard.OnboardEvent
import com.example.myapplication.presentation.screens.onboard.OnboardingViewModel
import com.example.myapplication.ui.theme.AppStyle

@Composable
internal fun AppBar(modifierTitle: Modifier = Modifier, modifierNext: Modifier = Modifier) {
    val viewModel: OnboardingViewModel = hiltViewModel()
    Text(
        text = stringResource(id = R.string.lbl_language),
        modifier = modifierTitle,
        style = AppStyle.HeaderStyle()
    )

    Row(
        modifier = modifierNext, verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.clickable {
                viewModel.onEvent(OnboardEvent.Navigation(Router.Slider.router))
            },
            text = stringResource(id = R.string.lbl_next),
            style = AppStyle.HeaderStyle().copy(
                color = colorResource(id = R.color.h2CB252)
            )
        )

        Image(
            painter = painterResource(id = R.drawable.ic_next),
            contentDescription = "Next",
            colorFilter = ColorFilter.tint(colorResource(id = R.color.h2CB252))
        )
    }
}