package com.example.myapplication.presentation.screens.onboard.introduce.widgets

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.presentation.screens.onboard.OnboardingViewModel
import com.example.myapplication.presentation.screens.onboard.introduce.PAGE_COUNT
import com.example.myapplication.ui.theme.AppStyle
import com.example.myapplication.utils.enums.PageIntroduce

private val page = listOf(PageIntroduce.Introduce1, PageIntroduce.Introduce2)

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun PagerView(viewModel : OnboardingViewModel = hiltViewModel()) {
    HorizontalPager(
        modifier = Modifier.fillMaxSize(),
        state = viewModel.pagerState,
        pageSpacing = 0.dp,
        pageSize = PageSize.Fill,
        userScrollEnabled = true,
        reverseLayout = false,
        beyondBoundsPageCount = PAGE_COUNT,
        pageNestedScrollConnection = PagerDefaults.pageNestedScrollConnection(
            Orientation.Horizontal
        ),
        pageContent = {
            Column(
                modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top
            ) {
                TopLayout(it)
                BodyLayout(it)
            }
        }
    )
}

@Composable
private fun TopLayout(index: Int) {
    Image(
        painter = painterResource(id = page[index].icon),
        contentDescription = "Image Introduce",
        modifier = Modifier
            .fillMaxHeight(0.5f)
            .fillMaxWidth(0.8f),
        contentScale = ContentScale.Fit
    )
}

@Composable
private fun BodyLayout(index: Int, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth(0.8f)
            .fillMaxHeight(0.8f)
            .padding(start = 10.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = stringResource(page[index].title),
            style = AppStyle.titleStyleBold(),
            modifier = Modifier.padding(top = 20.dp)
        )

        Text(
            text = stringResource(page[index].des),
            style = AppStyle.bodyStyle(),
            modifier = Modifier.padding(top = 20.dp),
            overflow = TextOverflow.Ellipsis,
            maxLines = 4
        )
    }
}
