package com.finmanager.routinerandomaizer.domain.usecase

import com.finmanager.routinerandomaizer.domain.TaskControlInterface
import com.finmanager.routinerandomaizer.domain.models.Task
import com.finmanager.routinerandomaizer.domain.taskInterface.TaskInterface
import javax.inject.Inject

class CompleteTaskUseCase @Inject constructor(
    private val repository: TaskControlInterface
) {

    suspend fun execute(task: Task){
        repository.completeTask(
            Task(
                id = task.id,
                name = task.name,
                description = null
            )
        )
    }

}