package com.finmanager.routinerandomaizer

import com.finmanager.routinerandomaizer.data.Randomizer
import com.finmanager.routinerandomaizer.domain.models.Task
import kotlinx.coroutines.*
import org.junit.jupiter.api.Test

import org.junit.Assert.*


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun fullActiveTasksList() {
            // Context of the app under test.
            val taskList = listOf<Task>(
                Task(0, "1", "1"),
                Task(1, "1", "0"),
                Task(2, "1", "0"),
                Task(4, "1", "0"),
                Task(5, "1", "0"),
                Task(6, "1", "0"),
                Task(7, "1", "1")
            )
                val isFull = runBlocking { Randomizer().isListFull(taskList) }
                assertEquals(isFull, false)
    }

    @Test
    fun nullTasksList() {
        // Context of the app under test.

        val isFull = runBlocking { Randomizer().isListFull(null) }
        assertEquals(isFull, false)
    }
}