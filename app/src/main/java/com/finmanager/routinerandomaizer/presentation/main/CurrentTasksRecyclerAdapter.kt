package com.finmanager.routinerandomaizer.presentation.main

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
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


class CurrentTasksRecyclerAdapter @Inject constructor(
    private val CompleteTask:CompleteTaskUseCase,
    @ApplicationContext private val context: Context
) : RecyclerView.Adapter<CurrentTasksRecyclerAdapter.ViewHolder>() {

    private var singleTask = mutableListOf<Task>()

    class ViewHolder(val binding: CurrentTaskCardBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CurrentTaskCardBinding.inflate(
                LayoutInflater.from(parent.context),
            parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = singleTask[position]
        holder.binding.CurrentTaskName.text = currentItem.name
        holder.binding.checkBox.setOnCheckedChangeListener { buttonView, isChecked   ->
            if (isChecked) {
                CoroutineScope(Dispatchers.IO).launch {
                    delay(800)
                    CompleteTask.execute(currentItem)
                }
                buttonView.isChecked = false
                Toast.makeText(context,isChecked.toString(),Toast.LENGTH_SHORT).show()
            }
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