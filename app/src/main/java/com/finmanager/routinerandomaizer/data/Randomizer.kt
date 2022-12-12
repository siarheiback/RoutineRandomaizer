package com.finmanager.routinerandomaizer.data

import android.content.Context
import com.finmanager.routinerandomaizer.domain.models.TaskState
import com.finmanager.routinerandomaizer.domain.randomiser.RandomizerInterface
import com.finmanager.routinerandomaizer.domain.models.Task
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class Randomizer@Inject constructor(
    @ApplicationContext private val context: Context
) : RandomizerInterface {

   override suspend fun isListFull(list: List<Task>?): Boolean {
       return if (list!=null) {
           list.filter { it.isActive }.size  >= 3
       } else{
           false
       }
   }

   override suspend fun getRandomTask(list: List<Task>?): TaskState {
       val activeTasks = list?.asSequence()
           ?.filter { !it.isSleeping }
           ?.filter { !it.isActive }
           ?.toList()
       return  when (isListFull(list)){
           false -> {
               activeTasks?.randomOrNull()
                   ?.let { TaskState.Randomised(it) }
                   ?: TaskState.NoTasks
           }
           true ->{
               TaskState.TooMuch
           }

       }
   }

}