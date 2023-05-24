package com.example.myapplication.model

import androidx.annotation.DrawableRes

data class Display(
    var title: Int? = null,
    var titleStr: String? = "",
    var content: Int? = null,
    var contentStr : String? = "",
    @DrawableRes var iconRes : Int? = null,
    var isChecked : Boolean = false
    )