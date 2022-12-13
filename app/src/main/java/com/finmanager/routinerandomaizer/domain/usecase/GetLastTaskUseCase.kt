package com.finmanager.routinerandomaizer.domain.usecase

import androidx.lifecycle.LiveData
import com.finmanager.routinerandomaizer.ReturnTask
import com.finmanager.routinerandomaizer.domain.TaskControlInterface
import com.finmanager.routinerandomaizer.domain.models.Task
import com.finmanager.routinerandomaizer.domain.taskInterface.TaskInterface
import javax.inject.Inject

class GetLastTaskUseCase@Inject constructor(
    private val task: TaskInterface,
    private val returnTask: ReturnTask
) {

    fun execute(): Task {
       return task.getLastTask(returnTask.returnTask())
    }
}