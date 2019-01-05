package com.dwigg.tasuketefinal.ui.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import com.dwigg.tasuketefinal.R
import kotlinx.android.synthetic.main.dialog_confirmation.*

class ConfirmationDialog(
    context: Context,
    private val title: String,
    private val message: String,
    private val confirmListener: (Dialog) -> Unit
) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_confirmation)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        init()
    }

    private fun init() {
        tvDialogTitle.text = title
        tvDialogMessage.text = message
        btnConfirm.setOnClickListener { confirmListener(this) }
        btnCancel.setOnClickListener { dismiss() }
    }
}