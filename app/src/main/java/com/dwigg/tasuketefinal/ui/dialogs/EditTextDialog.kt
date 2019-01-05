package com.dwigg.tasuketefinal.ui.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import com.dwigg.tasuketefinal.R
import kotlinx.android.synthetic.main.dialog_edit_text.*

class EditTextDialog(
    context: Context,
    private val text: String,
    private val hint: String,
    private val listener: (String, Dialog) -> Unit
) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_edit_text)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        init()
    }

    private fun init() {
        etText.setText(text)
        etText.hint = hint
        btnCancel.setOnClickListener { dismiss() }
        btnSave.setOnClickListener { listener(etText.text.toString(), this) }
    }
}