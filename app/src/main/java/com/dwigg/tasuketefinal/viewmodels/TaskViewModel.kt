package com.dwigg.tasuketefinal.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.dwigg.tasuketefinal.data.entities.Task
import com.dwigg.tasuketefinal.data.repositories.ListItemRepository
import com.dwigg.tasuketefinal.data.repositories.TaskRepository

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val taskRepository = TaskRepository(application)
    private val listItemRepository = ListItemRepository(application)

    lateinit var task: Task

    fun getTaskById(taskId: Long) = taskRepository.getTaskById(taskId)

    fun updateTask(task: Task) = taskRepository.updateTask(task)

    fun deleteTask(taskId: Long) = taskRepository.deleteTask(taskId)

    fun getListItemById(listItemId: Long) = listItemRepository.getListItemById(listItemId)
}