package com.dwigg.tasuketefinal.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.dwigg.tasuketefinal.data.repositories.ListItemAndTasksRepository
import com.dwigg.tasuketefinal.data.repositories.ListItemRepository
import com.dwigg.tasuketefinal.data.repositories.TaskRepository

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val listItemRepository = ListItemRepository(application)
    private val taskRepository = TaskRepository(application)
    private val listItemAndTasksRepository = ListItemAndTasksRepository(application)

    fun getAllListItems() = listItemRepository.getAllListItems()

    fun getAllTasksByListItemId(listItemId: Long) = taskRepository.getAllTasksByListItemId(listItemId)

    fun getListItemAndTasksById(listItemId: Long) = listItemAndTasksRepository.getListItemAndTasksById(listItemId)

    fun getAllListItemAndTasks() = listItemAndTasksRepository.getAllListAndTasks()
}