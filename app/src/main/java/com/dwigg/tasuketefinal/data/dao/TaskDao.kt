package com.dwigg.tasuketefinal.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dwigg.tasuketefinal.data.entities.Task

@Dao
interface TaskDao {

    @Query("SELECT * FROM Task WHERE listItemId = :listItemId ORDER BY isDone asc")
    fun getAllTasksByListItemId(listItemId: Long): LiveData<List<Task>>

    @Query("SELECT * FROM Task WHERE id = :taskId")
    fun getTaskById(taskId: Long): LiveData<Task>

    @Insert
    fun insertTask(task: Task)

    @Update
    fun updateTask(task: Task)

    @Query("DELETE FROM Task WHERE id = :taskId")
    fun deleteTask(taskId: Long)

    @Query("DELETE FROM TASK WHERE listItemId = :listItemId & isDone = 1")
    fun deleteAllDoneTaskByListItemId(listItemId: Long)

    @Query("DELETE FROM Task WHERE listItemId = :listItemId")
    fun deleteAllTasksByListItemId(listItemId: Long)
}