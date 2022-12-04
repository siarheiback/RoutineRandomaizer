package com.finmanager.routinerandomaizer.presentation.taskList

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.finmanager.routinerandomaizer.databinding.TaskCardBinding
import com.finmanager.routinerandomaizer.domain.models.Task
import com.finmanager.routinerandomaizer.domain.usecase.CreateNewTaskUseCase
import com.finmanager.routinerandomaizer.domain.usecase.DeleteTaskUseCase
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class TaskListRecyclerAdapter @Inject constructor(
        private val DeleteTask: DeleteTaskUseCase,
        private val CreateNewTask: CreateNewTaskUseCase,
        @ApplicationContext private val context: Context,
    ) : RecyclerView.Adapter<TaskListRecyclerAdapter.ViewHolder>() {


        class ViewHolder(val binding: TaskCardBinding) : RecyclerView.ViewHolder(binding.root) {
            fun setData(item:Task){
                binding.apply {
                    TaskName.text = item.name.toString()
                }

            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                TaskCardBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
            )
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val currentItem = differ.currentList[position]
            holder.binding.position.text = holder.adapterPosition.toString()
            holder.setData(currentItem)
            holder.binding.imageButton.setOnClickListener(){
                CoroutineScope(Dispatchers.IO).launch {
                    DeleteTask.execute(currentItem )
                }

            }
        }


        override fun getItemCount(): Int {
            return differ.currentList.size
        }


        private val differCallback = object :DiffUtil.ItemCallback<Task>() {

            override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
                return oldItem == newItem
            }
        }
        val differ=AsyncListDiffer(this,differCallback )

}




