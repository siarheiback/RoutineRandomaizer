package com.finmanager.routinerandomaizer.db

import androidx.lifecycle.LiveData
import com.finmanager.routinerandomaizer.domain.models.Task
import com.finmanager.routinerandomaizer.domain.taskInterface.TaskInterface



class TasksRepository (private val tasksDAO: TasksDAO):TaskInterface{

    override suspend fun readAllData(): LiveData<List<Task>> = tasksDAO.readAllTasks()

    override suspend fun addTask (task: Task){
        tasksDAO.addTask(task)
    }
    override suspend fun deleteTask (task: Task){
        tasksDAO.deleteTask(task)
    }
    override suspend fun updateTask (task: Task){
        tasksDAO.updateTask(task)
    }


}