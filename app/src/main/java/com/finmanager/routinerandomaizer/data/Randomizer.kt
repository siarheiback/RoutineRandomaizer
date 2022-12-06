package com.finmanager.routinerandomaizer.data

import com.finmanager.routinerandomaizer.TaskState
import com.finmanager.routinerandomaizer.domain.randomiser.RandomizerInterface
import com.finmanager.routinerandomaizer.domain.models.Task
import javax.inject.Inject

class Randomizer@Inject constructor() : RandomizerInterface {

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

//   override suspend fun isListFull(list: List<Task>?): Boolean {
//       return if (list!=null) {list.filter { it.description == "1" }.size  >= 3}
//       else{
//           false
//       }
//   }

    override suspend fun isListFull(list: List<Task>?): Boolean? {
        var status:Boolean? = null
        val listSize = list?.filter { it.description == "1" }?.size

        if (listSize != null) {
            when {
                listSize > 2 -> status = true
                listSize < 3 -> status = false
            }
        }else{
            status = null
        }
        return status
    }



   override suspend fun getRandomTask(list: List<Task>?):TaskState{
       val activeTasks = list?.asSequence()
           ?.filter { it.description!="1" }
           ?.toList()
       return  when (isListFull(list)){
           false -> {
               activeTasks?.randomOrNull()?.let { TaskState.Active(it) }
                   ?: TaskState.NoTasks(Task(0, "У вас нет доступных задач", null))
           }
           true ->{
               TaskState.ToMuch(Task(0,"У вас слишком много активных задач", null))
           }
           null -> {
               TaskState.NoTasks(Task(0,"У вас нет доступных задач", null))
           }
       }
   }

}