package com.finmanager.routinerandomaizer.data

import com.finmanager.routinerandomaizer.domain.randomiser.RandomizerInterface
import com.finmanager.routinerandomaizer.domain.models.Task
import javax.inject.Inject

class Randomizer@Inject constructor() : RandomizerInterface {

    override suspend fun getRandomTask(list: List<Task>?):Task{
      return  when (isListFull(list)){
          false -> { list?.asSequence()
              ?.filter { it.description!="1" }
              ?.toList()
              ?.randomOrNull()
              ?:Task(0,"У вас нет доступных задач", null)
          }
          true ->{Task(0,"У вас слишком много активных задач", null) }
      }
    }

    override suspend fun isListFull(list: List<Task>?): Boolean {
        return if (list!=null) {list.filter { it.description == "1" }.size  >= 3}
        else{
            false
        }
    }

}