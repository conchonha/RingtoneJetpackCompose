package com.example.myapplication.domain.model

import androidx.annotation.DrawableRes
import java.util.UUID

data class Display(
    var title: Int? = null,
    var titleStr: String? = "",
    var content: Int? = null,
    var contentStr : String? = "",
    @DrawableRes var iconRes : Int? = null,
    var isChecked : Boolean = false,
    var id : String = UUID.randomUUID().toString()
    )