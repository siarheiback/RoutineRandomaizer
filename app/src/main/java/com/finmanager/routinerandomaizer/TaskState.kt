package com.finmanager.routinerandomaizer

import com.finmanager.routinerandomaizer.domain.models.Task

sealed class TaskState{
    class Inactive(var msg: Task) : TaskState()
    class Randomised(var msg: Task) : TaskState()
    class NoTasks(var msg: Task) : TaskState()
    class ToMuch(var msg: Task) : TaskState()
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
                    sleepDate = null)
            }
            else -> {
                TODO()
            }
        }
    }
}

