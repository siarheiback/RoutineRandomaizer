package com.finmanager.routinerandomaizer

import com.finmanager.routinerandomaizer.data.DateUtil
import com.finmanager.routinerandomaizer.data.Randomizer
import com.finmanager.routinerandomaizer.data.TaskController
import com.finmanager.routinerandomaizer.domain.models.Task
import dagger.hilt.android.AndroidEntryPoint
import junit.framework.Assert
import kotlinx.coroutines.runBlocking
import org.junit.experimental.categories.Categories
import org.junit.jupiter.api.Test
import javax.inject.Inject


class DateControlTest {
    @Inject lateinit var taskController:TaskController
    //@Inject lateinit var dateUtil:DateUtil
    @Test
    fun toWakeTest() {
        // Context of the app under test.
        val toWake = runBlocking {
            DateUtil().toWake(
                Task(0, "1", "1",
                    isSleeping = false,
                    isActive = false,
                    period = 1,
                    sleepDate = null,
                    wakeUpDate = "05.12.2022"
                )
            )
        }
        Assert.assertEquals(toWake, false)
    }

    @Test
    fun wakeUpTest() {
        // Context of the app under test.
        val taskList = listOf<Task>(
            Task(0, "1", "1",
                isSleeping = false,
                isActive = false,
                period = 1,
                sleepDate = null,
                wakeUpDate = null
            ),
//            Task(1, "1", "1"),
//            Task(2, "1", "0"),
//            Task(4, "1", "0"),
//            Task(5, "1", "0"),
//            Task(6, "1", "0"),
//            Task(7, "1", "1")1
        )
        val wakedTask = runBlocking {
            taskController.wakeUpTask(taskList)
            }
        Assert.assertEquals(wakedTask, false)
    }
}