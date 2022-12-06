package com.finmanager.routinerandomaizer.presentation.taskList

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.finmanager.routinerandomaizer.R
import com.finmanager.routinerandomaizer.databinding.CurrentTaskCardBinding
import com.finmanager.routinerandomaizer.databinding.TaskCardBinding
import com.finmanager.routinerandomaizer.domain.models.Task
import com.finmanager.routinerandomaizer.domain.usecase.CreateNewTaskUseCase
import com.finmanager.routinerandomaizer.domain.usecase.DeleteTaskUseCase
import com.finmanager.routinerandomaizer.presentation.main.CurrentTasksRecyclerAdapter
import com.finmanager.routinerandomaizer.presentation.main.TaskActionListener
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

interface TaskListActionListener{
    fun deleteTask(task: Task)
}

class TaskListRecyclerAdapter @Inject constructor(
    private val taskListActionListener: TaskListActionListener
    ) : RecyclerView.Adapter<TaskListRecyclerAdapter.ViewHolder>(), View.OnClickListener  {


        class ViewHolder(private val binding: TaskCardBinding) : RecyclerView.ViewHolder(binding.root) {
            @SuppressLint("ResourceAsColor")
            fun setData(item:Task, id: Int){
                binding.apply {
                    TaskName.text = item.name.toString()
                    position.text = id.toString()
                    deleteButton.tag = item
                    editButton.tag = item
                    if(item.description!=null){
                        TaskBackground.setBackgroundColor(R.color.purple_200)
                        buttons.visibility = View.GONE
                        itemView.tag = item
                    }

                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = TaskCardBinding.inflate(inflater, parent, false)
            binding.deleteButton.setOnClickListener(this)
            return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val currentItem = differ.currentList[position]
            holder.setData(currentItem, position)
        }

        override fun onClick(v: View) {
            val task = v.tag as Task
            taskListActionListener.deleteTask(task)
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




