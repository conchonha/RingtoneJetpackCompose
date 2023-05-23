package com.example.myapplication.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SlideIntroduce() {
    ConstraintLayout() {
        val (text, button) = createRefs()
        HorizontalPager(
            pageCount = 2,
        ) {
            Column(
                modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top
            ) {
                TopLayout(it)
                BodyLayout(it)
            }
        }

        Button(onClick = {

        }, modifier = Modifier
            .constrainAs(button) {
                bottom.linkTo(parent.bottom, margin = 20.dp)
                end.linkTo(parent.end, margin = 20.dp)
            }
            .height(35.dp), shape = RectangleShape, colors = ButtonDefaults.buttonColors(
            colorResource(id = R.color.button)
        )) {
            Text(
                text = stringResource(id = R.string.lbl_get_started),
                style = AppStyle.bodyStyleNoTheme()
            )
        }
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
private fun BodyLayout(index: Int) {
    Column(
        modifier = Modifier
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

@Preview(showBackground = true, name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewSlideIntroduce() {
    MyApplicationTheme {
        SlideIntroduce()
    }
}