package com.finmanager.routinerandomaizer.data

import com.finmanager.routinerandomaizer.domain.TaskControlInterface
import com.finmanager.routinerandomaizer.domain.models.Task
import com.finmanager.routinerandomaizer.domain.taskInterface.TaskInterface
import javax.inject.Inject

class TaskController@Inject constructor(
    private val repository: TaskInterface
):TaskControlInterface {

    override fun activateTask() {
        TODO("Not yet implemented")
    }

    override suspend fun acceptTask(task: Task) {
        repository.updateTask(task)
    }

    override fun declineTask() {
        TODO("Not yet implemented")
    }

    override suspend fun getActiveTasks(task: Task) {
        TODO("Not yet implemented")
    }

    override suspend fun completeTask(task: Task) {
        repository.updateTask(task)
    }


}