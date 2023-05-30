package com.example.myapplication.utils

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.core.view.isVisible
import kotlinx.coroutines.delay

object DialogUtils {
    val isLoadingDialog: SingleLiveEvent<Boolean> = SingleLiveEvent()
    private val TAG by lazy { "DialogUtils" }

    @Volatile
    private var countApi = 0

    @Volatile
    private var dialog: Dialog? = null

    fun showLoading() {
        isLoadingDialog.postValue(true)
    }

    fun hideLoading() {
        isLoadingDialog.postValue(false)
    }

    fun showLoadingDialog(context: Context) {
        if (countApi == 0) {
//            dialog = Dialog(context).apply {
//                setContentView(R.layout.dialog_loading)
//                window?.setBackgroundDrawableResource(android.R.color.transparent)
//                setCancelable(false)
//            }
//            dialog?.show()
        }
        countApi++
    }

    fun dismissDialog() {
        countApi--
        if (countApi <= 0) {
            dialog?.dismiss()
            countApi = 0
        }
    }

    suspend fun showToast(context: Context, title: String, duration: Long) {
//        Dialog(context).apply {
//            setContentView(
//                LayoutCustomToastBinding.inflate(layoutInflater)
//                    .apply { txtTitle.text = title }.root
//            )
//            window?.apply {
//                setBackgroundDrawableResource(android.R.color.transparent)
//                setLayout(
//                    LinearLayout.LayoutParams.MATCH_PARENT,
//                    LinearLayout.LayoutParams.MATCH_PARENT
//                )
//            }
//            setCancelable(true)
//            show()
//            delay(duration)
//            dismiss()
//            cancel()
//        }
    }

    fun showAlertDialogConfirm(
        context: Context,
        title: String = "",
        message: String = "",
//        lblOke: String = context.getString(com.sangtb.androidlibrary.R.string.lbl_oke),
//        lblCancel: String = context.getString(com.sangtb.androidlibrary.R.string.lbl_cancel),
        isVisibleCancel: Boolean = false,
        onConfirm: (() -> Unit)? = null,
    ) {
//        val dialog = Dialog(context)
//        val view = DialogConfirmAlertBinding.inflate(LayoutInflater.from(context)).apply {
//            if (isVisibleCancel) {
//                tvCancel.isVisible = true
//            }
//            txtTitle.text = title
//            txtContent.text = message
//            txtOke.apply {
//                setOnClickListener {
//                    dialog.dismiss()
//                    onConfirm?.invoke()
//                }
//                text = lblOke
//            }
//            tvCancel.setOnClickListener {
//                dialog.dismiss()
//            }
//        }
//        dialog.apply {
//            setContentView(view.root)
//            window?.apply {
//                setBackgroundDrawableResource(android.R.color.transparent)
//                attributes = WindowManager.LayoutParams().apply {
//                    width = WindowManager.LayoutParams.MATCH_PARENT
//                    height = WindowManager.LayoutParams.MATCH_PARENT
//                }
//            }
//            setCancelable(true)
//            show()
//        }
    }

    fun clearLoading() {
        dialog?.dismiss()
        dialog = null
    }
}