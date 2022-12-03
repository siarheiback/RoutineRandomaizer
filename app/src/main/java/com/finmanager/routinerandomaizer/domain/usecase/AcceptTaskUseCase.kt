package com.finmanager.routinerandomaizer.domain.usecase

import com.finmanager.routinerandomaizer.domain.TaskControlInterface
import com.finmanager.routinerandomaizer.domain.models.Task
import javax.inject.Inject

class AcceptTaskUseCase@Inject constructor(
    private val TaskController: TaskControlInterface
) {

    suspend fun execute(task: Task){
        TaskController.acceptTask(
                Task(
                    id = task.id,
                    name = task.name,
                    description = "1"
                )
        )
    }
}