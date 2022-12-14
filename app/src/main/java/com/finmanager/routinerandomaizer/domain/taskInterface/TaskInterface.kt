package com.finmanager.routinerandomaizer.domain.taskInterface

import androidx.lifecycle.LiveData
import com.finmanager.routinerandomaizer.domain.models.Task

interface TaskInterface {
    fun readAllData(): LiveData<List<Task>>
    suspend fun addTask (task: Task)
    suspend fun deleteTask (task: Task)
    suspend fun updateTask (task: Task)
    fun getLastTask (id: Int) : Task
}
