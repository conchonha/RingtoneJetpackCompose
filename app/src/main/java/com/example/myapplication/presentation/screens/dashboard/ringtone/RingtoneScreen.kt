package com.example.myapplication.presentation.screens.dashboard.ringtone

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.R
import com.example.myapplication.domain.model.CategoryResponse
import com.example.myapplication.presentation.screens.dashboard.DashBoardViewModel
import com.example.myapplication.presentation.screens.dashboard.nav.widgets.LocalViewModel

@Composable
fun Ringtone(modifier: Modifier = Modifier, viewModel: DashBoardViewModel? = LocalViewModel.current) {
    val data : State<List<CategoryResponse>> = viewModel?.list1?.collectAsState() ?: return

    Box(
        modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.bg))
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
           item {
               Text(text = "SangTB")
           }


            itemsIndexed(data.value){_, item ->
                Text(text = item.name.toString())
            }

            item {
                Text(text = "SangTB")
            }
        }
    }
}