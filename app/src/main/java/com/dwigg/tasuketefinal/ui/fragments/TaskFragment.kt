package com.dwigg.tasuketefinal.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dwigg.tasuketefinal.R
import com.dwigg.tasuketefinal.data.entities.Task
import com.dwigg.tasuketefinal.ui.activities.MainActivity
import com.dwigg.tasuketefinal.ui.dialogs.AddEditNoteDialog
import com.dwigg.tasuketefinal.ui.dialogs.ConfirmationDialog
import com.dwigg.tasuketefinal.ui.dialogs.EditTextDialog
import com.dwigg.tasuketefinal.ui.dialogs.TransferTaskDialog
import com.dwigg.tasuketefinal.utils.Constants
import com.dwigg.tasuketefinal.viewmodels.TaskViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_task.*

class TaskFragment : Fragment() {

    private lateinit var viewModel: TaskViewModel
    private var listItemId = 0L
    private var taskId = 0L

    companion object {
        fun newInstance(listItemId: Long, taskItemId: Long) = TaskFragment().apply {
            arguments = Bundle().apply {
                putLong(Constants.listItemIdKey, listItemId)
                putLong(Constants.taskIdKey, taskItemId)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_task, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TaskViewModel::class.java)

        init()
    }

    private fun init() {
        initializeData()
        initializeObserver()
        initializeBottomAppBar()
        initializeButtonListeners()
    }

    private fun initializeData() {
        listItemId = arguments!!.getLong(Constants.listItemIdKey)
        taskId = arguments!!.getLong(Constants.taskIdKey)
    }

    private fun initializeObserver() {
        viewModel.getTaskById(taskId).observe(this, Observer {task ->
            viewModel.task = task
            updateView(task)

            viewModel.getListItemById(task.listItemId).observe(this, Observer { listItem ->
                tvListLabel.text = listItem.title
            })
        })
    }

    private fun updateView(task: Task) {
        tvTaskTitle.text = task.title
        if (task.note.isNotEmpty()) {
            tvNote.text = task.note
            tvNote.setTextColor(ContextCompat.getColor(context!!, R.color.colorText))
        } else {
            tvNote.text = getString(R.string.add_note)
            tvNote.setTextColor(ContextCompat.getColor(context!!, R.color.colorDivider))
        }
    }

    private fun initializeBottomAppBar() {
        val mainActivity = (activity as MainActivity)
        mainActivity.hideFab()
        mainActivity.bottomAppBar.menu.clear()
    }

    private fun initializeButtonListeners() {
        btnBack.setOnClickListener { (activity as MainActivity).onBackPressed() }
        tvTaskTitle.setOnClickListener { openEditTextDialog() }
        btnTransferList.setOnClickListener { openTransferListDialog() }
        btnAddEditReminder.setOnClickListener { openAddEditReminderDialog() }
        btnAddEditNote.setOnClickListener { openAddEditNoteDialog() }
        btnDelete.setOnClickListener { deleteTask() }
    }

    private fun openEditTextDialog() {
        val editTextDialog = EditTextDialog(
            context!!,
            viewModel.task.title,
            getString(R.string.task_title)
        ) { taskTitle, dialog ->
            viewModel.task.title = taskTitle
            viewModel.updateTask(viewModel.task)
            dialog.dismiss()
        }
        editTextDialog.show()
    }

    private fun openTransferListDialog() {
        val transferTaskDialog = TransferTaskDialog.newInstance(listItemId) { listItem, dialog ->
            viewModel.task.listItemId = listItem.id
            viewModel.updateTask(viewModel.task)
            dialog.dismiss()
        }
        transferTaskDialog.show(fragmentManager!!, transferTaskDialog.tag)
    }

    private fun openAddEditReminderDialog() {
        Toast.makeText(context!!, "WIP", Toast.LENGTH_SHORT).show()
    }

    private fun openAddEditNoteDialog() {
        val addEditNoteDialog = AddEditNoteDialog(
            context!!, viewModel.task.note
        ) { note, dialog ->
            saveNote(note)
            dialog.dismiss()
        }
        addEditNoteDialog.show()
    }

    private fun saveNote(note: String) {
        viewModel.task.note = note
        viewModel.updateTask(viewModel.task)
    }

    private fun deleteTask() {
        val confirmationDialog = ConfirmationDialog(
            context!!,
            getString(R.string.warning),
            getString(R.string.warning_message_delete_task)
        ) {
            viewModel.deleteTask(taskId)
            it.dismiss()
            (activity as MainActivity).onBackPressed()
        }
        confirmationDialog.show()
    }
}