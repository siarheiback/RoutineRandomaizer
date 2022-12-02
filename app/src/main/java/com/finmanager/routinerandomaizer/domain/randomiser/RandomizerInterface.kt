package com.finmanager.routinerandomaizer.domain.randomiser

import com.finmanager.routinerandomaizer.domain.models.Task

interface RandomizerInterface {

    suspend fun getRandomTask(list: List<Task>?):Task?

}