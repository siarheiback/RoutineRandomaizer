package com.finmanager.routinerandomaizer.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.finmanager.routinerandomaizer.domain.models.Task


@Dao
interface TasksDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask (task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask (task: Task)

    @Query("SELECT * FROM tasks_database ORDER BY id ASC")
    suspend fun readAllTasks(): LiveData<List<Task>>

}