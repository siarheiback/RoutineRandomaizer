package com.finmanager.routinerandomaizer.presentation.taskList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.finmanager.routinerandomaizer.domain.models.Task
import com.finmanager.routinerandomaizer.domain.usecase.LoadTasksListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val LoadTasksList: LoadTasksListUseCase
): ViewModel() {

    private var _readAllData = MutableLiveData<List<Task>>()
    val readAllData:LiveData<List<Task>> = _readAllData

        val readAllData1 = LoadTasksList.execute()


}