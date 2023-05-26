package com.example.myapplication.presentation.screens.onboard.introduce.widgets

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.R
import com.example.myapplication.presentation.navigations.Router
import com.example.myapplication.presentation.screens.onboard.OnboardEvent
import com.example.myapplication.presentation.screens.onboard.OnboardingViewModel
import com.example.myapplication.ui.theme.AppStyle

@Composable
internal fun ButtonGetStarted(modifier: Modifier = Modifier,viewModel: OnboardingViewModel = hiltViewModel()) {
    Button(
        onClick = {
            viewModel.onEvent(OnboardEvent.Navigation(Router.DashBoard.router))
        },
        modifier = modifier.height(35.dp),
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            colorResource(id = R.color.h2CB252)
        )
    ) {
        Text(
            text = stringResource(id = R.string.lbl_get_started),
            style = AppStyle.bodyStyleNoTheme()
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun IndicatorView(modifier: Modifier = Modifier, viewModel : OnboardingViewModel = hiltViewModel()) {
    Row(modifier.padding(start = 15.dp)) {
        (0..1).forEach {
            Box(
                Modifier
                    .height(7.dp)
                    .width(7.dp)
                    .background(
                        color = if (it == viewModel.pagerState.currentPage) colorResource(id = R.color.h2CB252) else colorResource(
                            id = R.color.bg1
                        ), shape = CircleShape
                    )
            )
            Spacer(modifier = Modifier.width(10.dp))
        }
    }
}