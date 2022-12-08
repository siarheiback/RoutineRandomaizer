package com.finmanager.routinerandomaizer.domain.usecase

import android.os.Build
import com.finmanager.routinerandomaizer.data.DateUtilInterface
import com.finmanager.routinerandomaizer.domain.TaskControlInterface
import com.finmanager.routinerandomaizer.domain.models.Task
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class CompleteTaskUseCase @Inject constructor(
    private val repository: TaskControlInterface,
    private val dateUtil: DateUtilInterface
) {


    suspend fun execute(task: Task){
        repository.completeTask(
            Task(
                id = task.id,
                name = task.name,
                description = task.description,
                isSleeping = true,
                period = task.period,
                sleepDate = dateUtil.now(),
                wakeUpDate = dateUtil.getWakeUpDate(task)
            )
        )
    }
}