package com.dwigg.tasuketefinal.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.dwigg.tasuketefinal.data.entities.ListItem
import com.dwigg.tasuketefinal.data.entities.Task
import com.dwigg.tasuketefinal.data.repositories.ListItemRepository
import com.dwigg.tasuketefinal.data.repositories.TaskRepository

class TaskListViewModel(application: Application) : AndroidViewModel(application) {

    private val listItemRepository = ListItemRepository(application)
    private val taskRepository = TaskRepository(application)
    lateinit var listItem: ListItem

    fun getListItemById(listItemId: Long) = listItemRepository.getListItemById(listItemId)

    fun deleteListItemById(listItemId: Long) = listItemRepository.deleteListItemById(listItemId)

    fun getAllTasksByListItemId(listItemId: Long) = taskRepository.getAllTasksByListItemId(listItemId)

    fun updateTask(task: Task) = taskRepository.updateTask(task)

    fun deleteAllDoneTaskByListItemId(listItemId: Long) = taskRepository.deleteAllDoneTaskByListItemId(listItemId)

    fun deleteAllTasksByListItemId(listItemId: Long) = taskRepository.deleteAllTasksByListItemId(listItemId)

    fun updateListItem(listItem: ListItem) = listItemRepository.updateListItem(listItem)
}