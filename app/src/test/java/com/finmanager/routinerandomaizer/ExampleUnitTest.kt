package com.finmanager.routinerandomaizer

import com.finmanager.routinerandomaizer.data.Randomizer
import com.finmanager.routinerandomaizer.domain.models.Task
import com.finmanager.routinerandomaizer.domain.models.TaskState
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.*
import org.junit.jupiter.api.Test
import javax.inject.Inject


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Inject lateinit var randomizer: Randomizer
    private val taskList = listOf<Task>(
        Task(0, "1", "1",
            isSleeping = false,
            isActive = false,
            period = 1,
            sleepDate = null,
            wakeUpDate = null
        ),
        Task(1, "1", "1",
            isSleeping = false,
            isActive = false,
            period = 1,
            sleepDate = null,
            wakeUpDate = null
        ),
        Task(2, "1", "1",
            isSleeping = false,
            isActive = false,
            period = 1,
            sleepDate = null,
            wakeUpDate = null
        ),
        Task(3, "1", "1",
            isSleeping = false,
            isActive = false,
            period = 1,
            sleepDate = null,
            wakeUpDate = null
        )

    )

    @Test
    fun fullActiveTasksList() {
            // Context of the app under test.

                val isFull = runBlocking { randomizer.isListFull(taskList) }
                assertEquals(isFull, false)
    }

    @Test
    fun nullTasksList() {
        // Context of the app under test.

        val isFull = runBlocking { randomizer.isListFull(null) }
        assertEquals(isFull, false)
    }

    @Test
    fun getNoTask() {
        // Context of the app under test.
        val testTask = TaskState.NoTasks
        val task = runBlocking { randomizer.getRandomTask(null) }
        assertEquals(task, testTask)
    }


    @Test
    fun getTooMuchTasks() {
        // Context of the app under test.
        val testTask = TaskState.TooMuch
        val task = runBlocking { randomizer.getRandomTask(taskList) }
        assertEquals(task, testTask)
    }
}