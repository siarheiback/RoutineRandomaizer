package com.finmanager.routinerandomaizer.domain.usecase

import com.finmanager.routinerandomaizer.db.TasksRepository
import com.finmanager.routinerandomaizer.domain.models.Task
import com.finmanager.routinerandomaizer.domain.taskInterface.TaskInterface
import javax.inject.Inject

class CreateNewTaskUseCase@Inject constructor(
    private val repository: TaskInterface
) {
    suspend fun execute(task: Task){
        repository.addTask(task)
    }
}