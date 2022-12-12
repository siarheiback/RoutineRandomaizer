package com.finmanager.routinerandomaizer.presentation.main

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.finmanager.routinerandomaizer.domain.models.TaskState
import com.finmanager.routinerandomaizer.domain.models.Task
import com.finmanager.routinerandomaizer.domain.usecase.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val GetRandomTask: GetRandomTaskUseCase,
    private val LoadTasksList: LoadTasksListUseCase,
    private val AcceptTask: AcceptTaskUseCase,
    private val CompleteTask: CompleteTaskUseCase,
    private val WakeUpUseCase: WakeUpUseCase

) : ViewModel() {

    private var _state = MutableLiveData<Boolean>()
    val state: LiveData<Boolean> = _state

    private var _randomTask = MutableLiveData<TaskState?>()
    val randomTask: LiveData<TaskState?> = _randomTask

    var taskList = LoadTasksList.execute()

    init {

    }

    fun setDefaultTask(){
        _randomTask.postValue(TaskState.Default)
    }

    fun wake(list: List<Task>){
        viewModelScope.launch(Dispatchers.IO) {
            WakeUpUseCase.execute(list)
        }
    }
    fun getTask(list: List<Task>) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.postValue(true)
            delay(2000)
            _randomTask.postValue(GetRandomTask.execute(list))
            _state.postValue(false)
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

    @SuppressLint("ResourceAsColor")
    fun controller(view:View, text:TextView, task: TaskState?, button: FloatingActionButton){
        when(task){
            is TaskState.Randomised -> {
                view.visibility = View.VISIBLE
                text.text = task.msg.name.toString()
                button.isClickable = false
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
                button.isClickable = true
            }
        }
    }
}
