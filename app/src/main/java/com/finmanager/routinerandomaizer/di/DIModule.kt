package com.finmanager.routinerandomaizer.di

import com.finmanager.routinerandomaizer.db.TasksDAO
import com.finmanager.routinerandomaizer.db.TasksRepository
import com.finmanager.routinerandomaizer.domain.taskInterface.TaskInterface
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class DIModule {

    @Binds
    @ViewModelScoped
    abstract fun  bindsTaskInterface(
        TaskImpl: TasksRepository
    ): TaskInterface

}