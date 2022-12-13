package com.finmanager.routinerandomaizer.domain.usecase

import com.finmanager.routinerandomaizer.ReturnTask
import com.finmanager.routinerandomaizer.domain.TaskControlInterface
import com.finmanager.routinerandomaizer.domain.taskInterface.TaskInterface
import javax.inject.Inject

class GetLastTaskUseCase@Inject constructor(
    private val task: TaskInterface,
    private val returnTask: ReturnTask
) {

    fun execute (){
        task.getLastTask(returnTask.returnTask())
    }
}