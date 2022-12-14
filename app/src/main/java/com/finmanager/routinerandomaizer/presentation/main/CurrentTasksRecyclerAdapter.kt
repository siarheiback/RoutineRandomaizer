package com.finmanager.routinerandomaizer.presentation.main

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.finmanager.routinerandomaizer.R
import com.finmanager.routinerandomaizer.databinding.CurrentTaskCardBinding
import com.finmanager.routinerandomaizer.databinding.TaskCardBinding
import com.finmanager.routinerandomaizer.domain.models.Task
import com.finmanager.routinerandomaizer.domain.usecase.CompleteTaskUseCase
import com.finmanager.routinerandomaizer.presentation.taskList.TaskListFragmentDirections
import com.finmanager.routinerandomaizer.presentation.taskList.TaskListRecyclerAdapter
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton


interface TaskActionListener{
    fun completeTask(task: Task)
}

class CurrentTasksRecyclerAdapter (
    private val taskActionListener:TaskActionListener
) : RecyclerView.Adapter<CurrentTasksRecyclerAdapter.ViewHolder>(), View.OnClickListener {

    class ViewHolder(private val binding: CurrentTaskCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setData(item:Task){
            binding.apply {
                CurrentTaskName.text = item.name.toString()
                itemView.tag = item
                checkBox.tag = item
                checkBox.isChecked = false

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CurrentTaskCardBinding.inflate(inflater, parent, false)
        binding.checkBox.setOnClickListener(this)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onClick(v: View) {
        val task = v.tag as Task
            taskActionListener.completeTask(task)
    }


    private val differCallback = object : DiffUtil.ItemCallback<Task>() {

        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }
    }
    val differ= AsyncListDiffer(this,differCallback )


}

