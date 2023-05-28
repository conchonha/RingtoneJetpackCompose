package com.example.myapplication.utils

import com.example.myapplication.R
import com.example.myapplication.app.MyApplication
import com.example.myapplication.domain.model.DashBoardItemNav
import com.example.myapplication.domain.model.Display
import com.example.myapplication.presentation.navigations.Router

object Const {
    const val BASE_URL = "https://script.google.com/macros/s/AKfycbyh0-2zECW5vJaif2S-F3Mfp7Ohw9d8JIV_bh4pIEa3hyUSMWfykmIRFMm4xsR7kd8P/"
    const val IS_NIGHT_MODE = "isNightMode"
    const val KEY_LANGUAGE = "keyLanguage"
    const val KEY_FIRST_LAUNCH_LANGUAGE = "KEY_FIRST_LAUNCH_LANGUAGE"
    const val DATABASE_NAME = "RingtoneAppDatabase"
    const val ACTION_RECEIVE_RESULT_SET_GIF = "android.intent.action.WALLPAPER_CHANGED"
    const val POLICY = "https://www.freeprivacypolicy.com/live/f4068437-5503-4dfb-88f7-1a022660d0d2"

    //index
    const val INDEX_O = 0
    const val INDEX_1 = 1
    const val INDEX_2 = 2
    const val INDEX_3 = 3
    const val INDEX_4 = 4

    //successfully
    const val KEY_TOAST = "KeyToast"
    const val PACKAGE = "package"

    //ringtone detail
    const val KEY_RINGTONE_DETAIL = "keyRingToneDetail"
    const val KEY_RINGTONE_DIALOG = "keyRingToneDialog"

    //introduce
    const val KEY_FIRST_LAUNCH_INTRODUCE = "keyIntroduce"

    //componentList
    const val KEY_TAB = "keyTab"
    const val KeyPositionSlide = "keyPositionSlide"

    //billing sub / in-app purchase
    const val BUY_YEAR_ID = "buy_year_sub"
    const val BUY_MONTH_ID = "buy_month_sub"

    //bottom nav
    const val BOTTOM_NAV_COUNT = 5
    const val RINGTONE_TAB = 0
    const val WALLPAPER_TAB = 1
    const val LIVE_WALLPAPER_TAB = 2
    const val CALL_TAB = 3
    const val HOME_TAB = 4

    const val IMAGE_CATEGORY_TYPE = 5
    const val ID_IMAGE_CATEGORY: Int = -1
    const val KEY_IS_FAVORITE_LIST_CLICK = "keyIsFavoriteListClick"


    //key tab bottomNav
    const val TYPE_RINGTONE = "RingTone"
    const val TYPE_WALLPAPER_CALL_SCREEN = "WallpaperCallscreen"
    const val TYPE_GIFT = "LiveWallPaper"

    //Key ComponentList
    const val KEY_CATEGORY_NAV = "categoryNav"

    //language
    const val SCALE_LANGUAGE_DES = 0.8f

    //contact
    const val KEY_FILE = "keyFile"
    const val KEY_CONTACT_IMAGE = "contactImage"
    const val CONTACT_KEY = "contact"

    //EMPTY VALUE
    const val EMPTY_VALUE_STR = ""
    const val BOOL_DEFAULT = false

    fun getListLanguage(): MutableList<Display> {
        val language = SharePrefs.getInstance().get<String>(KEY_LANGUAGE)
        return mutableListOf(
            Display(
                title = R.string.english,
                iconRes = R.drawable.ic_english,
                isChecked = language == getString(R.string.const_english_en),
                content = R.string.const_english_en
            ), Display(
                title = R.string.spain,
                iconRes = R.drawable.ic_spain,
                isChecked = language == getString(R.string.const_spain_es),
                content = R.string.const_spain_es
            ), Display(
                title = R.string.portugal,
                iconRes = R.drawable.ic_portugal,
                isChecked = language == getString(R.string.const_portugal_pt_PT),
                content = R.string.const_portugal_pt_PT
            ), Display(
                title = R.string.hindi,
                iconRes = R.drawable.ic_hindi,
                isChecked = language == getString(R.string.const_hindi_hi),
                content = R.string.const_hindi_hi
            ), Display(
                title = R.string.germany,
                iconRes = R.drawable.ic_germany,
                isChecked = language == getString(R.string.const_germany_de),
                content = R.string.const_germany_de
            ), Display(
                title = R.string.france,
                iconRes = R.drawable.ic_france,
                isChecked = language == getString(R.string.const_france_fr_FR),
                content = R.string.const_france_fr_FR
            ), Display(
                title = R.string.chinese,
                iconRes = R.drawable.ic_china,
                isChecked = language == getString(R.string.const_chines_zh),
                content = R.string.const_chines_zh
            ), Display(
                title = R.string.vietnam,
                iconRes = R.drawable.ic_vietnam,
                isChecked = language == getString(R.string.const_vietnam_vi),
                content = R.string.const_vietnam_vi
            )
        ).apply {
            for (element in this) {
                if (element.isChecked) {
                    return this
                }
            }
            this.firstOrNull()?.isChecked = true
        }
    }

    fun getNavItems() = listOf(
        DashBoardItemNav(
            getString(R.string.lbl_ringtone), R.drawable.ic_ringtone, Router.Ringtone.router
        ), DashBoardItemNav(
            getString(R.string.lbl_wallpaper), R.drawable.ic_image, Router.Wallpaper.router
        ), DashBoardItemNav(
            getString(R.string.lbl_live_wallpaper),
            R.drawable.ic_live_image,
            Router.LiveWallPaper.router
        ), DashBoardItemNav(
            getString(R.string.lbl_call), R.drawable.ic_call, Router.Call.router
        )
    )

    private fun getString(textId: Int) = MyApplication.application.getString(textId)
}