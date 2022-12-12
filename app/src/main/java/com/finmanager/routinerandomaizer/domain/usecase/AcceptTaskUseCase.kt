package com.finmanager.routinerandomaizer.domain.usecase

import com.finmanager.routinerandomaizer.domain.models.TaskState
import com.finmanager.routinerandomaizer.domain.TaskControlInterface
import javax.inject.Inject

class AcceptTaskUseCase@Inject constructor(
    private val TaskController: TaskControlInterface
) {

    suspend fun execute(task: TaskState){

        TaskController.acceptTask(task.getTask())

    }
}