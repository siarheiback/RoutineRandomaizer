package com.finmanager.routinerandomaizer.data.randomizerImpl

import com.finmanager.routinerandomaizer.domain.randomiser.RandomizerInterface
import com.finmanager.routinerandomaizer.domain.models.Task
import javax.inject.Inject

class Randomizer@Inject constructor() : RandomizerInterface {

    override suspend fun getRandomTask(list: List<Task>):Task?{
       // TODO("Add filter for inactive and current tasks")
      return list.randomOrNull()
    }

}