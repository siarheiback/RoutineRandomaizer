package com.finmanager.routinerandomaizer.domain.usecase

import androidx.lifecycle.LiveData
import com.finmanager.routinerandomaizer.domain.models.Task
import com.finmanager.routinerandomaizer.domain.taskInterface.TaskInterface
import javax.inject.Inject

class LoadTasksListUseCase@Inject constructor(
    private val repository: TaskInterface
) {
    fun execute():LiveData<List<Task>>{
      return repository.readAllData()
    }
}