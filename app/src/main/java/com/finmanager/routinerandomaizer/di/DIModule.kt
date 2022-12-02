package com.finmanager.routinerandomaizer.di

import android.content.Context
import androidx.room.Room
import com.finmanager.routinerandomaizer.data.randomizerImpl.Randomizer
import com.finmanager.routinerandomaizer.db.TaskDatabase
import com.finmanager.routinerandomaizer.db.TaskDatabase.Companion.dbName
import com.finmanager.routinerandomaizer.db.TasksDAO
import com.finmanager.routinerandomaizer.db.TasksRepository
import com.finmanager.routinerandomaizer.domain.randomiser.RandomizerInterface
import com.finmanager.routinerandomaizer.domain.taskInterface.TaskInterface
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
    abstract fun  bindsTaskInterface(
        TaskImpl: TasksRepository
    ): TaskInterface

    @Binds
    @ViewModelScoped
    abstract fun bindsRandomizerInterface(
        RandomizerImpl: Randomizer
    ): RandomizerInterface

}

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
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