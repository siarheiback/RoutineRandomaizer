package com.finmanager.routinerandomaizer

import com.finmanager.routinerandomaizer.domain.models.Task

sealed class TaskState{
    class Inactive(var msg: Task) : TaskState()
    class Randomised(var msg: Task) : TaskState()
    object NoTasks : TaskState()
    object TooMuch : TaskState()
    class Active(var msg: Task) : TaskState()
    class Sleeping(var msg: Task) : TaskState()

    fun getTask():Task{
        return when(this){
            is Randomised ->{
                Task(id =  msg.id,
                    name = msg.name,
                    description = null,
                    isSleeping = false,
                    isActive = true,
                    period = 1,
                    sleepDate = null,
                    wakeUpDate = null)
            }
            is NoTasks -> {
                Task(id = 0,
                    name = "context.getString(R.string.noTasks)",
                    description = null,
                    isSleeping = false,
                    period = 1,
                    sleepDate = null,
                    wakeUpDate = null)
            }
            is TooMuch -> {
                Task(id = 0,
                    name = "context.getString(R.string.TooMushTasks)",
                    description = null,
                    isSleeping = false,
                    period = 1,
                    sleepDate = null,
                    wakeUpDate = null)
            }
            else -> {
                TODO()
            }
        }
    }
}

