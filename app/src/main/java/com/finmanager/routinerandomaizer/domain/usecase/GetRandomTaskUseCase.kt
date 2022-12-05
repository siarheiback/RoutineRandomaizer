package com.finmanager.routinerandomaizer.domain.usecase

import com.finmanager.routinerandomaizer.TaskState
import com.finmanager.routinerandomaizer.domain.models.Task
import com.finmanager.routinerandomaizer.domain.randomiser.RandomizerInterface
import javax.inject.Inject

class GetRandomTaskUseCase @Inject constructor(
    private val repository: RandomizerInterface
) {

    suspend fun execute(task: List<Task>): TaskState {
        return repository.getRandomTask(task)
    }

}