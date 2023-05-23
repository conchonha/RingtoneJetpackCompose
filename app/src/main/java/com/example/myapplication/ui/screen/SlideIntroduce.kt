package com.example.myapplication.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.myapplication.R
import com.example.myapplication.enum.PageIntroduce
import com.example.myapplication.ui.theme.AppStyle
import com.example.myapplication.ui.theme.MyApplicationTheme

private val page = listOf(PageIntroduce.Introduce1, PageIntroduce.Introduce2)
private const val PAGE_COUNT = 2

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SlideIntroduce() {
    val pagerState = rememberPagerState()
    val currentPage = remember { mutableStateOf(0) }

    LaunchedEffect(key1 = pagerState.currentPage, block = {
        currentPage.value = pagerState.currentPage
    })

    ConstraintLayout {
        val (row, button) = createRefs()

        HorizontalPager(
            pageCount = PAGE_COUNT,
            state = pagerState,
        ) {
            Column(
                modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top
            ) {
                TopLayout(it)
                BodyLayout(it)
            }
        }

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
            .fillMaxWidth(0.7f)
            .fillMaxHeight(0.8f)
            .padding(start = 10.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = stringResource(page[index].title),
            style = AppStyle.titleStyle(),
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

        }, modifier = modifier
            .height(35.dp), shape = RectangleShape, colors = ButtonDefaults.buttonColors(
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
                    .background(color =  if (it == currentPage.value) colorResource(id = R.color.h2CB252) else colorResource(
                        id = R.color.black
                    ), shape = CircleShape)
            )
            Spacer(modifier = Modifier.width(10.dp))
        }
    }
}

@Preview(showBackground = true, name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewSlideIntroduce() {
    MyApplicationTheme {
        SlideIntroduce()
    }
}