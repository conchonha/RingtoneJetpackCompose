package com.example.myapplication.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import com.example.myapplication.R
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
    Column(modifier = modifier.padding(horizontal = 20.dp)) {
        TextElement()
        LanguageFirstItem()
        TextElement(R.string.lbl_other_language,Modifier.padding(top = 15.dp))
        LanguageListItem()
    }
}

@Composable
fun LanguageListItem() {
    Const.getListLanguage().forEach {

    }
}

@Composable
private fun TextElement(res: Int = R.string.lbl_suggested_language,modifier: Modifier = Modifier) {
    Text(
        text = stringResource(id = res),
        style = AppStyle.titleStyle().copy(
            color = colorResource(
                id = R.color.hD7979696
            )
        ),
        modifier = modifier.padding(start = 20.dp, bottom = 15.dp)
    )
}

@Composable
private fun LanguageFirstItem(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(colorResource(R.color.bg_card_language), RoundedCornerShape(6.dp))
            .padding(horizontal = 10.dp, vertical = 7.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = "Tiếng Anh", style = AppStyle.titleStyle())
            Text(text = "en", style = AppStyle.smallType())
        }
        Icon(
            painter = painterResource(R.drawable.ic_done),
            contentDescription = "Done",
            tint = colorResource(
                id = R.color.h2CB252
            )
        )
    }
}


@Composable
@Preview(showBackground = true)
private fun PreviewLanguage() {
    MyApplicationTheme() {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize()) {
            Language(navController = null)
        }
    }
}