package com.finmanager.routinerandomaizer.domain.models

sealed class TaskState{
    class Inactive(var msg: Task) : TaskState()
    class Randomised(var msg: Task) : TaskState()
    object NoTasks : TaskState()
    object TooMuch : TaskState()
    object Default : TaskState()
    class Active(var msg: Task) : TaskState()
    class Sleeping(var msg: Task) : TaskState()


    fun getTask():Task{
        return when(this){
            is Default ->{
                Task(id =  0,
                    name = "Получить задачу",
                    description = null,
                    isSleeping = false,
                    isActive = false,
                    period = 1,
                    sleepDate = null,
                    wakeUpDate = null)
            }
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
                    name = "Нет задач",
                    description = null,
                    isSleeping = false,
                    period = 1,
                    sleepDate = null,
                    wakeUpDate = null)
            }
            is TooMuch -> {
                Task(id = 0,
                    name = "Слишком много активынх задач",
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

