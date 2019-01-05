package com.dwigg.tasuketefinal.ui.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import com.dwigg.tasuketefinal.R
import kotlinx.android.synthetic.main.dialog_add_edit_note_dialog.*

class AddEditNoteDialog(
    context: Context,
    private val note: String,
    private val listener: (String, AddEditNoteDialog) -> Unit
) :
    Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_add_edit_note_dialog)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        init()
    }

    private fun init() {
        etNote.setText(note)
        btnCancel.setOnClickListener { dismiss() }
        btnSave.setOnClickListener { listener(etNote.text.toString(), this) }
    }
}