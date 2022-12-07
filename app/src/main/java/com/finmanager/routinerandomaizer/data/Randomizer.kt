package com.finmanager.routinerandomaizer.data

import android.content.Context
import com.finmanager.routinerandomaizer.R
import com.finmanager.routinerandomaizer.TaskState
import com.finmanager.routinerandomaizer.domain.randomiser.RandomizerInterface
import com.finmanager.routinerandomaizer.domain.models.Task
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class Randomizer@Inject constructor(
    @ApplicationContext private val context: Context
) : RandomizerInterface {

   /* override suspend fun getRandomTask(list: List<Task>?):Task{
      return  when (isListFull(list)){
          false -> { list?.asSequence()
              ?.filter { it.description!="1" }
              ?.toList()
              ?.randomOrNull()
              ?:Task(0,"У вас нет доступных задач", null)
          }
          true ->{Task(0,"У вас слишком много активных задач", null) }
      }
    }*/

   override suspend fun isListFull(list: List<Task>?): Boolean {
       return if (list!=null) {
           list.filter { it.isSleeping }.size  >= 3
       } else{
           false
       }
   }

   override suspend fun getRandomTask(list: List<Task>?):TaskState{
       val activeTasks = list?.asSequence()
           ?.filter { !it.isSleeping }
           ?.toList()
       return  when (isListFull(list)){
           false -> {
               activeTasks?.randomOrNull()
                   ?.let { TaskState.Randomised(it) }
                   ?: TaskState.NoTasks(
                       Task(id = 0,
                           name =context.getString(R.string.noTasks),
                           description = null,
                           isSleeping = false,
                           period = 1,
                           sleepDate = null)
                   )
           }
           true ->{
               TaskState.ToMuch(
                   Task(id = 0,
                   name = context.getString(R.string.TooMushTasks),
                   description = null,
                   isSleeping = false,
                   period = 1,
                   sleepDate = null)
               )
           }

       }
   }

}