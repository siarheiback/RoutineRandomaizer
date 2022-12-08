package com.finmanager.routinerandomaizer.domain.usecase

import com.finmanager.routinerandomaizer.domain.TaskControlInterface
import com.finmanager.routinerandomaizer.domain.models.Task
import javax.inject.Inject

class WakeUpUseCase @Inject constructor(
    private val repository: TaskControlInterface,
){
    suspend fun execute(list:List<Task>?){
        repository.wakeUpTask(list)
    }
}