package com.example.myapplication.presentation.screens.onboard.introduce

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.enums.PageIntroduce
import com.example.myapplication.presentation.navigations.Router
import com.example.myapplication.presentation.navigations.navController
import com.example.myapplication.ui.theme.AppStyle

private val page = listOf(PageIntroduce.Introduce1, PageIntroduce.Introduce2)
private const val PAGE_COUNT = 2

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SlideIntroduce() {
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        PAGE_COUNT
    }

    val currentPage = remember { mutableStateOf(0) }

    /**
     * LaunchedEffect(Key1,Key2): Chỉ chạy 1 lần lúc khởi tạo compose,
     * và được kích hoạt lại khi các giá trị key1,key2 thay đổi giá trị
     * */
    LaunchedEffect(pagerState, pagerState.currentPage, block = {
        currentPage.value = pagerState.currentPage
    })

    ConstraintLayout(modifier = Modifier.background(colorResource(id = R.color.bg))) {
        val (row, button) = createRefs()

        PagerView(pagerState)

        BottomButton(modifier = Modifier.constrainAs(button) {
            bottom.linkTo(parent.bottom, margin = 20.dp)
            end.linkTo(parent.end, margin = 20.dp)
        })

        BottomIndicator(Modifier.constrainAs(row) {
            bottom.linkTo(button.bottom)
            top.linkTo(button.top)
            start.linkTo(parent.start)
        }, currentPage)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PagerView(pagerState: PagerState) {
    HorizontalPager(
        modifier = Modifier.fillMaxSize(),
        state = pagerState,
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

@Composable
private fun BottomButton(modifier: Modifier = Modifier) {
    Button(
        onClick = {
            navController.navigate(Router.Language.router)
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

@Composable
private fun BottomIndicator(modifier: Modifier = Modifier, currentPage: MutableState<Int>) {
    Row(modifier.padding(start = 15.dp)) {
        (0..1).forEach {
            Box(
                Modifier
                    .height(7.dp)
                    .width(7.dp)
                    .background(
                        color = if (it == currentPage.value) colorResource(id = R.color.h2CB252) else colorResource(
                            id = R.color.bg1
                        ), shape = CircleShape
                    )
            )
            Spacer(modifier = Modifier.width(10.dp))
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewSlideIntroduce() {
//    SlideIntroduce(null)
//}