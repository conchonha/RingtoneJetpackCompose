package com.example.myapplication.presentation.screens.onboard.introduce

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.R
import com.example.myapplication.presentation.screens.onboard.OnboardingViewModel
import com.example.myapplication.presentation.screens.onboard.introduce.widgets.ButtonGetStarted
import com.example.myapplication.presentation.screens.onboard.introduce.widgets.IndicatorView
import com.example.myapplication.presentation.screens.onboard.introduce.widgets.PagerView

const val PAGE_COUNT = 2

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SlideIntroduce() {
    val vm : OnboardingViewModel = hiltViewModel()
    vm.pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        PAGE_COUNT
    }

    /**
     * LaunchedEffect(Key1,Key2): Chỉ chạy 1 lần lúc khởi tạo compose,
     * và được kích hoạt lại khi các giá trị key1,key2 thay đổi giá trị
     * */

    ConstraintLayout(modifier = Modifier.background(colorResource(id = R.color.bg))) {
        val (row, button) = createRefs()

        PagerView()

        ButtonGetStarted(modifier = Modifier.constrainAs(button) {
            bottom.linkTo(parent.bottom, margin = 20.dp)
            end.linkTo(parent.end, margin = 20.dp)
        })

        IndicatorView(Modifier.constrainAs(row) {
            bottom.linkTo(button.bottom)
            top.linkTo(button.top)
            start.linkTo(parent.start)
        })
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSlideIntroduce() {
    SlideIntroduce()
}