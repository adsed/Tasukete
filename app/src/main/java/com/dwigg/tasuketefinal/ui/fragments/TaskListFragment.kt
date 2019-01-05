package com.dwigg.tasuketefinal.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.dwigg.tasuketefinal.R
import com.dwigg.tasuketefinal.adapters.TaskListAdapter
import com.dwigg.tasuketefinal.data.entities.Task
import com.dwigg.tasuketefinal.ui.activities.MainActivity
import com.dwigg.tasuketefinal.ui.dialogs.ConfirmationDialog
import com.dwigg.tasuketefinal.ui.dialogs.EditTextDialog
import com.dwigg.tasuketefinal.utils.Constants
import com.dwigg.tasuketefinal.viewmodels.TaskListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_task_list.*

class TaskListFragment : Fragment() {

    private var listItemId: Long = 0L
    private lateinit var viewModel: TaskListViewModel
    private lateinit var taskListAdapter: TaskListAdapter

    companion object {
        fun newInstance(listItemId: Long) = TaskListFragment().apply {
            arguments = Bundle().apply {
                putLong(Constants.listItemIdKey, listItemId)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_task_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TaskListViewModel::class.java)

        init()
    }

    private fun init() {
        initializeItemListId()
        initializeListTitle()
        initializeBottomAppBar()
        initializeBackButton()
        initializeTaskListAdapter()
        initializeTaskList()
    }

    private fun initializeItemListId() {
        arguments?.getLong(Constants.listItemIdKey)?.let {
            listItemId = it
        }
    }

    private fun initializeListTitle() {
        viewModel.getListItemById(listItemId).observe(this, Observer {
            viewModel.listItem = it
            tvListName.text = it.title
        })
    }

    private fun initializeBottomAppBar() {
        val mainActivity = (activity as MainActivity)
        mainActivity.setFabAlignmentToCenter()
        mainActivity.fab.setOnClickListener {
            val newTaskFragment = NewTaskFragment.newInstance(listItemId)
            newTaskFragment.show(fragmentManager!!, Constants.tagNewTaskFragment)
        }
        initializeBottomAppBarMenu(mainActivity)
    }

    private fun initializeBottomAppBarMenu(mainActivity: MainActivity) {
        mainActivity.bottomAppBar.replaceMenu(R.menu.menu_fragment_task_list)
        mainActivity.bottomAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.btnDeleteDoneItems -> btnDeleteDoneItemsOnClick()
                R.id.btnEditListTitle -> btnEditListTitleOnClick()
                R.id.btnDeleteList -> btnDeleteListOnClick()
            }
            true
        }
    }

    private fun btnDeleteDoneItemsOnClick() {
        val confirmationDialog = ConfirmationDialog(
            context!!,
            getString(R.string.warning),
            getString(R.string.warning_message_delete_all_done_items)
        ) {
            viewModel.deleteAllDoneTaskByListItemId(listItemId)
            it.dismiss()
        }
        confirmationDialog.show()
    }

    private fun btnEditListTitleOnClick() {
        val editTextDialog = EditTextDialog(
            context!!,
            viewModel.listItem.title,
            getString(R.string.list_title)
        ) { listTitle, dialog ->
            viewModel.listItem.title = listTitle.toUpperCase()
            viewModel.updateListItem(viewModel.listItem)
            dialog.dismiss()
        }
        editTextDialog.show()
    }

    private fun btnDeleteListOnClick() {
        val confirmationDialog = ConfirmationDialog(
            context!!,
            getString(R.string.warning),
            getString(R.string.warning_message_delete_item_list)
        ) {
            viewModel.deleteAllTasksByListItemId(listItemId)
            viewModel.deleteListItemById(listItemId)
            it.dismiss()
            (activity as MainActivity).onBackPressed()
        }
        confirmationDialog.show()
    }

    private fun initializeBackButton() {
        btnBack.setOnClickListener {
            (activity as MainActivity).onBackPressed()
        }
    }

    private fun initializeTaskListAdapter() {
        taskListAdapter = TaskListAdapter(
            { task -> taskStatusOnClick(task) },
            { task -> taskItemOnClick(task) }
        )

        viewModel.getAllTasksByListItemId(listItemId).observe(this, Observer {
            if (it!!.isEmpty()) {
                tvEmpty.visibility = View.VISIBLE
                rvTaskList.visibility = View.GONE
            } else {
                tvEmpty.visibility = View.GONE
                rvTaskList.visibility = View.VISIBLE
                taskListAdapter.updateTaskList(it)
            }
        })
    }

    private fun initializeTaskList() {
        rvTaskList.layoutManager = LinearLayoutManager(context)
        rvTaskList.itemAnimator = DefaultItemAnimator()
        rvTaskList.adapter = taskListAdapter
    }

    private fun taskStatusOnClick(task: Task) {
        task.isDone = !task.isDone
        viewModel.updateTask(task)
    }

    private fun taskItemOnClick(task: Task) {
        val editAndViewTaskFragment = TaskFragment.newInstance(listItemId, task.id)
        fragmentManager!!.beginTransaction()
            .replace(R.id.container, editAndViewTaskFragment, Constants.tagEditAndViewTaskFragment)
            .addToBackStack(null)
            .commit()
    }
}
