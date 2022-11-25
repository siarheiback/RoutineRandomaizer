package com.finmanager.routinerandomaizer.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.finmanager.routinerandomaizer.domain.models.Task


@Database(entities = [Task::class], version = 1, exportSchema = true)
//@TypeConverters(converter::class) //добавил для эксеримента TODO(add json converter)
abstract class TaskDatabase : RoomDatabase(){

    abstract fun tasksDao(): TasksDAO

    public companion object{
        const val dbName = "tasks_database"

        @Volatile
        private var INSTANCE: TaskDatabase? = null

        fun getDatabase(context: Context): TaskDatabase {
            val tempInstance = INSTANCE
            if(tempInstance !=null){
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    dbName
                ).build()
                INSTANCE = instance
                return instance
            }
        }


    }
}