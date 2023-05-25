package com.example.myapplication.presentation.screens.onboard.language

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.myapplication.R
import com.example.myapplication.presentation.screens.onboard.language.widgets.AppBar
import com.example.myapplication.presentation.screens.onboard.language.widgets.Body
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun Language() {
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
            top.linkTo(parent.top, margin = 15.dp)
        })

        Body(Modifier.constrainAs(body) {
            top.linkTo(next.bottom, 35.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
        })
    }
}

@Composable
@Preview(showBackground = true)
 fun PreviewLanguage() {
    MyApplicationTheme() {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize()) {
            Language()
        }
    }
}