package com.geeks.lesson_6_1.ui.task

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.geeks.lesson_6_1.databinding.ItemTaskBinding
import com.geeks.lesson_6_1.model.Task

class TaskAdapter(
    var list: ArrayList<Task>,
    private val onLongClickTask: (Task) -> Unit
) : Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(private val binding: ItemTaskBinding) : ViewHolder(binding.root){
        fun binds(task: Task) = with(binding){
            tvTitle.text = task.title
            tvDescription.text = task.description
            itemView.setOnLongClickListener {
                onLongClickTask(task)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder =
        TaskViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.binds(list[position])
    }
}