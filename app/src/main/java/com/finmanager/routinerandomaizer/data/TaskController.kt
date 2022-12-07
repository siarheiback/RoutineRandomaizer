package com.finmanager.routinerandomaizer.data

import android.os.Build
import com.finmanager.routinerandomaizer.domain.TaskControlInterface
import com.finmanager.routinerandomaizer.domain.models.Task
import com.finmanager.routinerandomaizer.domain.taskInterface.TaskInterface
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class TaskController@Inject constructor(
    private val repository: TaskInterface,
    private val dateUtil: DateUtilInterface
):TaskControlInterface {





    override suspend fun wakeUpTask(list:List<Task>?) {

     /*   list?.filter { it.isSleeping }?.forEachIndexed { _, task ->
            if(task.sleepDate!=null && task.wakeUpDate!=null){
                dateUtil.toWake(task.sleepDate,task.wakeUpDate)
                if(dateutil(task.sleepDate).convertToDate() < task.wakeUpDate.convertToDate()){
                    repository.updateTask(
                        Task(
                            id =  task.id,
                            name = task.name,
                            description = task.description,
                            isSleeping = false,
                            isActive = false,
                            period = task.period,
                            sleepDate = null,
                            wakeUpDate = null)
                    )
                }
            }
        }*/
    }

    override suspend fun acceptTask(task: Task) {
        repository.updateTask(task)
    }

    override fun declineTask() {
        TODO("Not yet implemented")
    }

    override suspend fun completeTask(task: Task) {
        repository.updateTask(task)
    }


}