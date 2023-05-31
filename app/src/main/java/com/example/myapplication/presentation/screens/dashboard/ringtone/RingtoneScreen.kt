package com.example.myapplication.presentation.screens.dashboard.ringtone

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.component.ItemRingtone
import com.example.myapplication.domain.model.CategoryResponse
import com.example.myapplication.presentation.screens.dashboard.DashBoardViewModel
import com.example.myapplication.presentation.screens.dashboard.widgets.LocalViewModel

@Composable
fun Ringtone(modifier: Modifier = Modifier, viewModel: DashBoardViewModel? = LocalViewModel.current) {
    ItemRingtone()
}