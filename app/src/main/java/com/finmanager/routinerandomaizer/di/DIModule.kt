package com.finmanager.routinerandomaizer.di

import android.content.Context
import androidx.room.Room
import com.finmanager.routinerandomaizer.ReturnRandomizedTask
import com.finmanager.routinerandomaizer.ReturnTask
import com.finmanager.routinerandomaizer.data.DateUtil
import com.finmanager.routinerandomaizer.data.DateUtilInterface
import com.finmanager.routinerandomaizer.data.Randomizer
import com.finmanager.routinerandomaizer.data.TaskController
import com.finmanager.routinerandomaizer.db.TaskDatabase
import com.finmanager.routinerandomaizer.db.TaskDatabase.Companion.dbName
import com.finmanager.routinerandomaizer.db.TasksDAO
import com.finmanager.routinerandomaizer.db.TasksRepository
import com.finmanager.routinerandomaizer.domain.TaskControlInterface
import com.finmanager.routinerandomaizer.domain.randomiser.RandomizerInterface
import com.finmanager.routinerandomaizer.domain.taskInterface.TaskInterface
import com.finmanager.routinerandomaizer.domain.usecase.CompleteTaskUseCase
import com.finmanager.routinerandomaizer.domain.usecase.CreateNewTaskUseCase
import com.finmanager.routinerandomaizer.domain.usecase.DeleteTaskUseCase
import com.finmanager.routinerandomaizer.presentation.main.CurrentTasksRecyclerAdapter
import com.finmanager.routinerandomaizer.presentation.main.TaskActionListener

import com.finmanager.routinerandomaizer.presentation.taskList.TaskListRecyclerAdapter

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class DIModule {

    @Binds
    @ViewModelScoped
    abstract fun bindsRandomizerInterface(
        RandomizerImpl: Randomizer
    ): RandomizerInterface

    @Binds
    @ViewModelScoped
    abstract fun bindsDateUtilInterface(
        DateUtil: DateUtil
    ): DateUtilInterface

    @Binds
    @ViewModelScoped
    abstract fun bindsTaskControlInterface(
        TaskController: TaskController
    ): TaskControlInterface

    @Binds
    @ViewModelScoped
    abstract fun  bindsTaskInterface(
        TaskImpl: TasksRepository
    ): TaskInterface

    @Binds
    @ViewModelScoped
    abstract fun bindsReturnTask(
        TaskImpl: ReturnRandomizedTask
    ): ReturnTask
}

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {


    @Provides
    @Singleton
    fun provideTasksDao(appDatabase: TaskDatabase): TasksDAO {
        return appDatabase.tasksDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): TaskDatabase {
        return Room.databaseBuilder(
            appContext,
            TaskDatabase::class.java,
            dbName
        ).build()
    }

}