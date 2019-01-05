package com.dwigg.tasuketefinal.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.dwigg.tasuketefinal.data.entities.ListItemAndTasks

@Dao
interface ListItemAndTasksDao {

    @Query("SELECT * FROM List WHERE id = :listItemId ORDER BY title asc")
    fun getListItemAndTasksById(listItemId: Long): LiveData<ListItemAndTasks>

    @Query("SELECT * FROM List")
    fun getAllListItemAndTasks(): LiveData<List<ListItemAndTasks>>
}