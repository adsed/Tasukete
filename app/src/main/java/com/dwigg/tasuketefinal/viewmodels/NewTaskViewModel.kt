package com.dwigg.tasuketefinal.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.dwigg.tasuketefinal.data.entities.Task
import com.dwigg.tasuketefinal.data.repositories.TaskRepository
import com.dwigg.tasuketefinal.utils.DoAsync

class NewTaskViewModel(application: Application) : AndroidViewModel(application) {

    private val taskRepository = TaskRepository(application)
    private lateinit var task: MutableLiveData<Task>

    fun insertTask(taskTitle: String, listItemId: Long) {
        val task = getTask().value
        task!!.title = taskTitle
        task.listItemId = listItemId
        taskRepository.insertTask(task)
    }

    fun getTask(): MutableLiveData<Task> {
        if (!::task.isInitialized) {
            task = MutableLiveData()
            task.value = Task()
        }
        return task
    }

    fun setTaskNote(note: String) {
        val task = getTask().value
        task!!.note = note

        DoAsync {
            getTask().postValue(task)
        }.execute()
    }
}