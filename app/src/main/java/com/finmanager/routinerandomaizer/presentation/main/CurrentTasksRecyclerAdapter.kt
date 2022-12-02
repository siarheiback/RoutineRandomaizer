package com.finmanager.routinerandomaizer.presentation.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.finmanager.routinerandomaizer.databinding.TaskCardBinding
import com.finmanager.routinerandomaizer.domain.models.Task
import com.finmanager.routinerandomaizer.presentation.taskList.TaskListFragmentDirections
import com.finmanager.routinerandomaizer.presentation.taskList.TaskListRecyclerAdapter

class CurrentTasksRecyclerAdapter : RecyclerView.Adapter<CurrentTasksRecyclerAdapter.ViewHolder>() {
    private var singleTask = mutableListOf<Task>()
    class ViewHolder(val binding: TaskCardBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            TaskCardBinding.inflate(
                LayoutInflater.from(parent.context),
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
            this.singleTask.addAll(data.asSequence().filter { it.description=="1" }.toList())
        }

        notifyDataSetChanged()}
}