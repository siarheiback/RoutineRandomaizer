package com.finmanager.routinerandomaizer

import com.finmanager.routinerandomaizer.domain.models.Task

sealed class TaskState{
    class Inactive(var msg: Task) : TaskState()
    class Active(var msg: Task) : TaskState()
    class NoTasks(var msg: Task) : TaskState()
    class ToMuch(var msg: Task) : TaskState()
}

