package com.example.myapplication.enum

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.myapplication.R

sealed class PageIntroduce(
    @DrawableRes val icon: Int,
    @StringRes val title: Int,
    @StringRes val des: Int
) {
    object Introduce1 : PageIntroduce(
        R.drawable.ic_introduce_1,
        R.string.lbl_title_here_slide1,
        R.string.lbl_description_here_slide_1
    )

    object Introduce2 : PageIntroduce(
        R.drawable.ic_introduce_2,
        R.string.lbl_title_here_slide2,
        R.string.lbl_description_here_slide_2
    )
}