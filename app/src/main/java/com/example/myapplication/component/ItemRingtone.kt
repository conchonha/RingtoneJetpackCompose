package com.example.myapplication.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.myapplication.R
import com.example.myapplication.presentation.screens.dashboard.DashBoardViewModel
import com.example.myapplication.presentation.screens.dashboard.widgets.LocalViewModel
import com.example.myapplication.ui.theme.AppStyle

@Composable
@Preview(showBackground = true)
fun ItemRingtone(modifier: Modifier = Modifier) {
    val viewModel = LocalViewModel.current ?: return
    LinerView(modifier, viewModel)
}

private fun gridView() {
}

@Composable
private fun LinerView(modifier: Modifier = Modifier, viewModel: DashBoardViewModel) {
    LazyColumn(
        modifier
            .fillMaxSize()
            .padding(start = 15.dp, end = 15.dp, top = 15.dp, bottom = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(viewModel.tabState.value.datas) {
            ConstraintLayout(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
            ) {
                val (image, colum, next) = createRefs()

                ImageWithProgress(Modifier.constrainAs(image) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.wrapContent
                }, url = it.image.toString(), size = 60.dp)

                Image(
                    modifier = Modifier.constrainAs(next) {
                        top.linkTo(image.top)
                        bottom.linkTo(image.bottom)
                        end.linkTo(parent.end)
                    },
                    painter = painterResource(id = R.drawable.ic_next),
                    contentDescription = "Next"
                )

                Column(Modifier.constrainAs(colum) {
                    top.linkTo(image.top)
                    bottom.linkTo(image.bottom)
                    start.linkTo(image.end, 15.dp)
                    end.linkTo(next.start)
                    width = Dimension.fillToConstraints
                }, verticalArrangement = Arrangement.Center) {
                    Text(
                        text = it.name.toString(),
                        style = AppStyle.titleStyle(),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(text = "Total: ".plus(it.total.toString()), style = AppStyle.bodyStyle())
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}