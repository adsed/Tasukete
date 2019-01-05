package com.dwigg.tasuketefinal.data.repositories

import android.app.Application
import com.dwigg.tasuketefinal.data.AppDatabase

class ListItemAndTasksRepository(application: Application) {

    private val appDatabase = AppDatabase.getTestInstance(application)
    private val listItemAndTasksDao = appDatabase!!.listItemAndTaskDao()

    fun getListItemAndTasksById(listItemId: Long) = listItemAndTasksDao.getListItemAndTasksById(listItemId)

    fun getAllListAndTasks() = listItemAndTasksDao.getAllListItemAndTasks()
}