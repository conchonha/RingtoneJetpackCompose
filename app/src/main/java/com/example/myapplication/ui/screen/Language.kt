package com.example.myapplication.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.model.Display
import com.example.myapplication.ui.theme.AppStyle
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.utils.Const

@Composable
fun Language(navController: NavController?) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.black))
    ) {
        val (body, title, next) = createRefs()
        AppBar(modifierTitle = Modifier.constrainAs(title) {
            start.linkTo(parent.start)
            top.linkTo(next.top)
            bottom.linkTo(next.bottom)
            end.linkTo(parent.end)
        }, modifierNext = Modifier.constrainAs(next) {
            end.linkTo(parent.end)
            top.linkTo(parent.top, margin = 10.dp)
        })

        Body(Modifier.constrainAs(body) {
            top.linkTo(next.bottom, 25.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
        })
    }
}

@Composable
private fun AppBar(modifierTitle: Modifier = Modifier, modifierNext: Modifier = Modifier) {
    Text(
        text = stringResource(id = R.string.lbl_language),
        modifier = modifierTitle,
        style = AppStyle.HeaderStyle()
    )

    Row(
        modifier = modifierNext, verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.lbl_next), style = AppStyle.HeaderStyle().copy(
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

@Composable
private fun Body(modifier: Modifier = Modifier) {
    val displayState = remember { mutableStateOf(Const.getListLanguage().first { it.isChecked }) }

    Column(modifier = modifier.padding(horizontal = 20.dp)) {
        TextElement()
        LanguageItem(display = displayState.value, iconEnd = R.drawable.ic_done)
        TextElement(Modifier.padding(top = 15.dp), R.string.lbl_other_language)
        LazyColumn {
            items(Const.getListLanguage()) {
                LanguageItem(display = it, displayState = displayState)
            }
        }
    }
}

@Composable
private fun TextElement(
    modifier: Modifier = Modifier,
    res: Int = R.string.lbl_suggested_language,
) {
    Text(
        text = stringResource(id = res), style = AppStyle.titleStyle().copy(
            color = colorResource(
                id = R.color.hD7979696
            )
        ), modifier = modifier.padding(start = 20.dp, bottom = 15.dp)
    )
}

@Composable
private fun LanguageItem(
    modifier: Modifier = Modifier,
    display: Display,
    iconEnd: Int? = null,
    displayState: MutableState<Display>? = null
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(colorResource(R.color.bg_card_language), RoundedCornerShape(6.dp))
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .clickable {
                displayState?.value = display
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {
            if (iconEnd == null) Image(
                modifier = Modifier
                    .width(70.dp)
                    .height(50.dp)
                    .padding(end = 15.dp),
                painter = painterResource(id = display.iconRes!!),
                contentDescription = stringResource(
                    id = display.title!!
                ),
                contentScale = ContentScale.Fit
            )

            Column {
                Text(text = stringResource(id = display.title!!), style = AppStyle.titleStyle())
                Text(text = stringResource(id = display.content!!), style = AppStyle.smallType())
            }
        }


        val icon = iconEnd ?: if (displayState?.value?.title == display.title) R.drawable.ic_radio_checked else R.drawable.ic_radio
        Image(
            painter = painterResource(icon),
            contentDescription = "Done",
            colorFilter = ColorFilter.tint(
                colorResource(
                    id = R.color.h2CB252
                )
            ),
            modifier = Modifier
                .width(15.dp)
                .height(15.dp)
        )
    }
}


//@Composable
//@Preview(showBackground = true)
// fun PreviewLanguage() {
//    MyApplicationTheme() {
//        // A surface container using the 'background' color from the theme
//        Surface(modifier = Modifier.fillMaxSize()) {
//            Language(navController = null)
//        }
//    }
//}