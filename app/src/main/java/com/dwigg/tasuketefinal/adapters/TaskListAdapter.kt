package com.dwigg.tasuketefinal.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dwigg.tasuketefinal.R
import com.dwigg.tasuketefinal.data.entities.Task
import com.dwigg.tasuketefinal.utils.inflate
import kotlinx.android.synthetic.main.row_task_item.view.*

class TaskListAdapter(
    private val taskStatusOnClickListener: (Task) -> Unit,
    private val taskItemOnClickListener: (Task) -> Unit
) : RecyclerView.Adapter<TaskListAdapter.ViewHolder>() {

    private var taskList: List<Task> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.row_task_item))
    }

    override fun getItemCount(): Int = taskList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(taskList[position], taskStatusOnClickListener, taskItemOnClickListener)
    }

    fun updateTaskList(taskList: List<Task>) {
        this.taskList = taskList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            task: Task,
            taskStatusOnClickListener: (Task) -> Unit,
            taskItemOnClickListener: (Task) -> Unit
        ) = with(itemView) {
            tvTaskTitle.text = task.title
            rbTaskStatus.setChecked(task.isDone)
            if (task.isDone) {
                tvTaskTitle.background = resources.getDrawable(R.drawable.strike_through, null)
            } else {
                tvTaskTitle.background = null
            }
            rbTaskStatus.setOnClickListener { taskStatusOnClickListener(task) }
            setOnClickListener { taskItemOnClickListener(task) }
        }
    }
}