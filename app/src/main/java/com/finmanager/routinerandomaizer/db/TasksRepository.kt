package com.finmanager.routinerandomaizer.db

import androidx.lifecycle.LiveData
import com.finmanager.routinerandomaizer.domain.models.Task
import com.finmanager.routinerandomaizer.domain.taskInterface.TaskInterface
import javax.inject.Inject


class TasksRepository@Inject constructor(
    private val DAO: TasksDAO
    ) :TaskInterface{

    override suspend fun readAllData(): LiveData<List<Task>> = DAO.readAllTasks()

    override suspend fun addTask (task: Task){
        DAO.addTask(task)
    }
    override suspend fun deleteTask (task: Task){
       DAO.deleteTask(task)
    }
    override suspend fun updateTask (task: Task){
        DAO.updateTask(task)
    }


}