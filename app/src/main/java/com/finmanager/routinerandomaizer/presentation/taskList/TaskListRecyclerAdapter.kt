package com.finmanager.routinerandomaizer.presentation.taskList

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.finmanager.routinerandomaizer.databinding.TaskCardBinding
import com.finmanager.routinerandomaizer.domain.models.Task

class TaskListRecyclerAdapter : RecyclerView.Adapter<TaskListRecyclerAdapter.ViewHolder>() {
    private var singleTask = mutableListOf<Task>()
    class ViewHolder(val binding: TaskCardBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TaskCardBinding.inflate(LayoutInflater.from(parent.context),
                parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = singleTask[position]
            holder.binding.TaskName.text = currentItem.name
        holder.binding.TaskCard.setOnClickListener { view ->
            val action = TaskListFragmentDirections.actionTaskListFragmentToCurrentTaskFragment(currentItem)
            view.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return singleTask.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<Task>?) {
        this.singleTask.clear()
        if (data != null) {
            this.singleTask.addAll(data)
        }

        notifyDataSetChanged()}
}