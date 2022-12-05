package com.finmanager.routinerandomaizer.domain.usecase

import android.view.View
import com.finmanager.routinerandomaizer.TaskState
import com.finmanager.routinerandomaizer.domain.TaskControlInterface
import com.finmanager.routinerandomaizer.domain.models.Task
import javax.inject.Inject

class AcceptTaskUseCase@Inject constructor(
    private val TaskController: TaskControlInterface
) {

    suspend fun execute(task: TaskState){
        when(task){
            is TaskState.Active ->{
                TaskController.acceptTask(
                    Task(
                        id = task.msg.id,
                        name = task.msg.name,
                        description = "1"
                    )
                )
            }
          else -> {
              TODO("check")
          }
        }

    }
}