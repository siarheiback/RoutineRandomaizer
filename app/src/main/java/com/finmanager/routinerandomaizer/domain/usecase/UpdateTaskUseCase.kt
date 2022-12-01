package com.finmanager.routinerandomaizer.domain.usecase

import com.finmanager.routinerandomaizer.domain.models.Task
import com.finmanager.routinerandomaizer.domain.taskInterface.TaskInterface
import javax.inject.Inject

class UpdateTaskUseCase @Inject constructor(
    private val repository: TaskInterface
) {

    suspend fun execute(task: Task){
        repository.updateTask(task)
    }
}