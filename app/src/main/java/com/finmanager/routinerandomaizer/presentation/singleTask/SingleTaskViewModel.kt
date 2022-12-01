package com.finmanager.routinerandomaizer.presentation.singleTask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.finmanager.routinerandomaizer.db.TasksRepository
import com.finmanager.routinerandomaizer.domain.models.Task
import com.finmanager.routinerandomaizer.domain.usecase.CreateNewTaskUseCase
import com.finmanager.routinerandomaizer.domain.usecase.DeleteTaskUseCase
import com.finmanager.routinerandomaizer.domain.usecase.LoadTasksListUseCase
import com.finmanager.routinerandomaizer.domain.usecase.UpdateTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingleTaskViewModel@Inject constructor(
    private val createNewTask:CreateNewTaskUseCase,
):ViewModel() {

    fun newTask(task: Task){
        viewModelScope.launch(Dispatchers.IO){
            createNewTask.execute(task)
        }
    }

}