package com.finmanager.routinerandomaizer.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.finmanager.routinerandomaizer.domain.models.Task
import com.finmanager.routinerandomaizer.domain.usecase.AcceptTaskUseCase
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
    private val AcceptTask: AcceptTaskUseCase
) : ViewModel() {

    private var _randomTask = MutableLiveData<Task>()
    val randomTask: LiveData<Task> = _randomTask

    var taskList = LoadTasksList.execute()

    fun getTask(list: List<Task>) {
        viewModelScope.launch(Dispatchers.IO) {
            _randomTask.postValue(GetRandomTask.execute(list))
        }
    }

    fun acceptTask(task: Task){
        viewModelScope.launch(Dispatchers.IO){
            AcceptTask.execute(task)
        }
    }

    fun buttonController(state:Boolean){
        when (state){
            true->{}
            false->{}
            null->{}
        }
    }
}
