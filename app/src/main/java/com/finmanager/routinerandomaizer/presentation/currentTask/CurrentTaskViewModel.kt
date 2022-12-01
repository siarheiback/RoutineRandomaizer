package com.finmanager.routinerandomaizer.presentation.currentTask

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.finmanager.routinerandomaizer.domain.models.Task
import com.finmanager.routinerandomaizer.domain.usecase.CreateNewTaskUseCase
import com.finmanager.routinerandomaizer.domain.usecase.DeleteTaskUseCase
import com.finmanager.routinerandomaizer.domain.usecase.UpdateTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentTaskViewModel@Inject constructor(
    private val updateTask: UpdateTaskUseCase,
    private val deleteTask: DeleteTaskUseCase,
    savedStateHandle: SavedStateHandle,
): ViewModel()  {

    private val args = CurrentTaskFragmentArgs
        .fromSavedStateHandle(savedStateHandle)
    private val _task = args.currentTask
    val task = _task

    fun updateTask(task: Task){
        viewModelScope.launch(Dispatchers.IO){
            updateTask.execute(_task)
        }
    }

    fun deleteTask(){
        viewModelScope.launch(Dispatchers.IO){
            deleteTask.execute(_task)
        }
    }

}