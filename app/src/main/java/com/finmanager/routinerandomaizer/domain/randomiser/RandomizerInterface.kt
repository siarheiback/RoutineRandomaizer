package com.finmanager.routinerandomaizer.domain.randomiser

import com.finmanager.routinerandomaizer.domain.models.TaskState
import com.finmanager.routinerandomaizer.domain.models.Task

interface RandomizerInterface {

    //suspend fun getRandomTask(list: List<Task>?):Task?

    suspend fun isListFull(list:List<Task>?):Boolean?

    suspend fun getRandomTask(list: List<Task>?): TaskState
}