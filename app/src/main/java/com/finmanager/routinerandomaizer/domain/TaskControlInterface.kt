package com.finmanager.routinerandomaizer.domain

import com.finmanager.routinerandomaizer.domain.models.Task

interface TaskControlInterface {

    suspend fun wakeUpTask(list:List<Task>?)

    suspend fun acceptTask(task: Task)

    fun declineTask()

    suspend fun completeTask(task: Task)



}