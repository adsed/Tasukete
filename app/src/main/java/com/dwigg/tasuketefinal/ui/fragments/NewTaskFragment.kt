package com.dwigg.tasuketefinal.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dwigg.tasuketefinal.R
import com.dwigg.tasuketefinal.ui.dialogs.AddEditNoteDialog
import com.dwigg.tasuketefinal.utils.Constants
import com.dwigg.tasuketefinal.utils.setColor
import com.dwigg.tasuketefinal.viewmodels.NewTaskViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_new_task.*

class NewTaskFragment : BottomSheetDialogFragment() {

    private lateinit var viewModel: NewTaskViewModel
    private var listItemId: Long = 0L

    companion object {
        fun newInstance(listItemId: Long) = NewTaskFragment().apply {
            arguments = Bundle().apply {
                putLong(Constants.listItemIdKey, listItemId)
            }
        }
    }

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_new_task, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NewTaskViewModel::class.java)

        init()
    }

    private fun init() {
        initializeListItemId()
        initializeObservers()
        initializeButtonListener()
    }

    private fun initializeListItemId() {
        arguments?.getLong(Constants.listItemIdKey)?.let {
            listItemId = it
        }
    }

    private fun initializeObservers() {
        viewModel.getTask().observe(this, Observer { task ->
            toggleNote(task.note)
        })
    }

    private fun toggleNote(note: String) {
        if (note.isEmpty()) {
            imgNoteIcon.setColor(R.color.colorPrimary)
            tvNoteLabel.text = getString(R.string.add_note)
        } else {
            imgNoteIcon.setColor(R.color.colorAccent)
            tvNoteLabel.text = getString(R.string.edit_note)
        }
    }

    private fun initializeButtonListener() {
        btnBack.setOnClickListener { dismiss() }
        btnAddEditNote.setOnClickListener { openAddEditNoteDialog() }
        btnAddEditReminder.setOnClickListener { openAddEditReminderDialog() }
        btnSave.setOnClickListener { saveTask() }
    }

    private fun openAddEditNoteDialog() {
        val addEditNoteDialog = AddEditNoteDialog(
            context!!, viewModel.getTask().value!!.note
        ) { note, dialog ->
            saveNote(note)
            dialog.dismiss()
        }
        addEditNoteDialog.show()
    }

    private fun saveNote(note: String) {
        viewModel.setTaskNote(note)
    }

    private fun openAddEditReminderDialog() {
        Toast.makeText(context!!, "WIP", Toast.LENGTH_SHORT).show()
    }

    private fun saveTask() {
        val taskTitle = etTaskTitle.text.trim().toString()
        if (taskTitle.isEmpty()) {
            return
        }
        viewModel.insertTask(taskTitle, listItemId)
        dismiss()
    }
}