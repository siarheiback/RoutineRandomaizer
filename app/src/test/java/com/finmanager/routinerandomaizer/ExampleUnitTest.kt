package com.finmanager.routinerandomaizer

import com.finmanager.routinerandomaizer.data.Randomizer
import com.finmanager.routinerandomaizer.domain.models.Task
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.*
import org.junit.jupiter.api.Test




/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private val taskList = listOf<Task>(
        Task(0, "1", "1"),
        Task(1, "1", "1"),
        Task(2, "1", "1"),
        Task(4, "1", "0"),
        Task(5, "1", "0"),
        Task(6, "1", "0"),
        Task(7, "1", "1")
    )

    @Test
    fun fullActiveTasksList() {
            // Context of the app under test.

                val isFull = runBlocking { Randomizer().isListFull(taskList) }
                assertEquals(isFull, false)
    }

    @Test
    fun nullTasksList() {
        // Context of the app under test.

        val isFull = runBlocking { Randomizer().isListFull(null) }
        assertEquals(isFull, false)
    }

    @Test
    fun getNoTask() {
        // Context of the app under test.
        val testTask = TaskState.NoTasks(
            Task(0,"У вас нет доступных задач", null)
        )
        when(val task = runBlocking { Randomizer().getRandomTask(null) }){
            is TaskState.Randomised-> assertEquals(task.toString(), testTask)
            else -> {}
        }

    }


    @Test
    fun getTooMuchTasks() {
        // Context of the app under test.
        val testTask = TaskState.ToMuch(
            Task(0,"У вас слишком много активных задач", null)
        )
        val task = runBlocking { Randomizer().getRandomTask(taskList) }
        assertEquals(task, testTask)
    }
}