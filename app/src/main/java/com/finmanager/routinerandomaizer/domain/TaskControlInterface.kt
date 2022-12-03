package com.finmanager.routinerandomaizer.domain

import com.finmanager.routinerandomaizer.domain.models.Task

interface TaskControlInterface {

    fun activateTask()

    suspend fun acceptTask(task: Task)

    fun declineTask()

    suspend fun getActiveTasks(task: Task)

    suspend fun completeTask(task: Task)



}