package com.finmanager.routinerandomaizer.presentation.main

import android.view.View
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.finmanager.routinerandomaizer.TaskState
import com.finmanager.routinerandomaizer.domain.models.Task
import com.finmanager.routinerandomaizer.domain.usecase.AcceptTaskUseCase
import com.finmanager.routinerandomaizer.domain.usecase.CompleteTaskUseCase
import com.finmanager.routinerandomaizer.domain.usecase.GetRandomTaskUseCase
import com.finmanager.routinerandomaizer.domain.usecase.LoadTasksListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val GetRandomTask: GetRandomTaskUseCase,
    private val LoadTasksList: LoadTasksListUseCase,
    private val AcceptTask: AcceptTaskUseCase,
    private val CompleteTask: CompleteTaskUseCase
) : ViewModel() {

    private var _state = MutableLiveData<TaskState>()
    val state: LiveData<TaskState> = _state

    private var _randomTask = MutableLiveData<TaskState?>()
    val randomTask: LiveData<TaskState?> = _randomTask

    var taskList = LoadTasksList.execute()

    fun getTask(list: List<Task>) {
        viewModelScope.launch(Dispatchers.IO) {
            _randomTask.postValue(GetRandomTask.execute(list))
        }
    }

    fun acceptTask(task: TaskState){
        viewModelScope.launch(Dispatchers.IO){
            AcceptTask.execute(task)
            _randomTask.postValue(null)
        }
    }

    fun completeTask(task: Task){
        viewModelScope.launch(Dispatchers.IO){
            CompleteTask.execute(task)
        }
    }

    fun controller(view:View, text:TextView, task: TaskState?){
        when(task){
            is TaskState.Randomised -> {
                view.visibility = View.VISIBLE
                text.text = task.msg.name.toString()
            }
            is TaskState.Inactive ->{
                view.visibility = View.GONE
            }
            is TaskState.TooMuch ->{
                view.visibility = View.GONE
                text.text= task.getTask().name
            }
            is TaskState.NoTasks->{
                view.visibility = View.GONE
                text.text= task.getTask().name
            }

            else -> {
                view.visibility = View.GONE
                text.text= "Получить задачу"
            }
        }
    }
}
