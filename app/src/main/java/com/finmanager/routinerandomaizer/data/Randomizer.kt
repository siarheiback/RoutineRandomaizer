package com.finmanager.routinerandomaizer.data

import com.finmanager.routinerandomaizer.domain.randomiser.RandomizerInterface
import com.finmanager.routinerandomaizer.domain.models.Task
import javax.inject.Inject

class Randomizer@Inject constructor() : RandomizerInterface {

    override suspend fun getRandomTask(list: List<Task>?):Task{
      return list?.asSequence()
          ?.filter { it.description!="1" }
          ?.toList()
          ?.randomOrNull() ?: Task(0,"У вас нет доступных задач", null)
    }

}