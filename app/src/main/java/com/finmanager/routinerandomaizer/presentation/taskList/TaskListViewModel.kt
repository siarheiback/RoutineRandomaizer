package com.finmanager.routinerandomaizer.presentation.taskList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.finmanager.routinerandomaizer.domain.models.Task
import com.finmanager.routinerandomaizer.domain.usecase.CreateNewTaskUseCase
import com.finmanager.routinerandomaizer.domain.usecase.DeleteTaskUseCase
import com.finmanager.routinerandomaizer.domain.usecase.LoadTasksListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val LoadTasksList: LoadTasksListUseCase,
    private val createNewTask: CreateNewTaskUseCase,
    private val deleteTask: DeleteTaskUseCase,
): ViewModel() {

    private var _state = MutableLiveData<Boolean>()
    val state: LiveData<Boolean> = _state

    val readAllData = LoadTasksList.execute()

    fun newTask(task: Task){
        viewModelScope.launch(Dispatchers.IO){
            createNewTask.execute(task)
        }
    }

    fun deleteTask(task: Task){
        viewModelScope.launch(Dispatchers.IO){
            deleteTask.execute(task)
        }
    }


    fun create1000sleepingTasks(){
        viewModelScope.launch(Dispatchers.IO){
            _state.postValue(true)
            for (i in 1..10){
                createNewTask.execute(
                    Task(0, "1", "1",
                        isSleeping = true,
                        isActive = false,
                        period = 1,
                        sleepDate = "06.12.2022",
                        wakeUpDate = "07.12.2022"
                    )
                )
            }
            _state.postValue(false)
        }
    }
}