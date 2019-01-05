package com.dwigg.tasuketefinal.data.repositories

import android.app.Application
import com.dwigg.tasuketefinal.data.AppDatabase
import com.dwigg.tasuketefinal.data.entities.Task
import com.dwigg.tasuketefinal.utils.DoAsync
import java.util.*

class TaskRepository(application: Application) {

    private val appDatabase = AppDatabase.getTestInstance(application)
    private val taskDao = appDatabase!!.taskDao()

    fun getAllTasksByListItemId(listItemId: Long) = taskDao.getAllTasksByListItemId(listItemId)

    fun getTaskById(taskId: Long) = taskDao.getTaskById(taskId)

    fun insertTask(task: Task) {
        DoAsync {
            taskDao.insertTask(task)
        }.execute()
    }

    fun updateTask(task: Task) {
        task.modifiedAt = Date()
        DoAsync {
            taskDao.updateTask(task)
        }.execute()
    }

    fun deleteTask(taskId: Long) {
        DoAsync {
            taskDao.deleteTask(taskId)
        }.execute()
    }

    fun deleteAllDoneTaskByListItemId(listItemId: Long) {
        DoAsync {
            taskDao.deleteAllDoneTaskByListItemId(listItemId)
        }.execute()
    }

    fun deleteAllTasksByListItemId(listItemId: Long) {
        DoAsync {
            taskDao.deleteAllTasksByListItemId(listItemId)
        }.execute()
    }
}