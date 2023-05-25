package com.example.myapplication.presentation.screens.onboard.language.items

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.R
import com.example.myapplication.domain.model.Display
import com.example.myapplication.presentation.screens.onboard.OnboardEvent
import com.example.myapplication.presentation.screens.onboard.OnboardingViewModel
import com.example.myapplication.ui.theme.AppStyle
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.utils.Const


@Composable
internal fun Body(modifier: Modifier = Modifier, viewModel: OnboardingViewModel = hiltViewModel()) {
    Column(modifier = modifier.padding(horizontal = 20.dp)) {
        TextElement()
        LanguageItem(display = viewModel.languageState.value, iconEnd = R.drawable.ic_done)
        TextElement(Modifier.padding(top = 15.dp), R.string.lbl_other_language)
        LazyColumn {
            items(Const.getListLanguage()) {
                LanguageItem(display = it)
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
    viewModel: OnboardingViewModel = hiltViewModel()
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(colorResource(R.color.bg_card_language), RoundedCornerShape(6.dp))
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .clickable { viewModel.onEvent(OnboardEvent.ChoiceLanguage(display)) },
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


        Image(
            painter = painterResource(viewModel.getIconChoiceLanguage(iconEnd, display.title)),
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

@Composable
private fun previewBody(){
    LazyColumn {
        items(Const.getListLanguage()) {
            LanguageItemPreview(display = it)
        }
    }
}

@Composable
private fun LanguageItemPreview(
    modifier: Modifier = Modifier,
    display: Display,
    iconEnd: Int? = null,
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(colorResource(R.color.bg_card_language), RoundedCornerShape(6.dp))
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .clickable { },
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


        Image(
            painter = painterResource(R.drawable.ic_radio_checked),
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

@Composable
@Preview(showBackground = true)
fun PreviewBody() {
    MyApplicationTheme() {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize()) {
            previewBody()
        }
    }
}
