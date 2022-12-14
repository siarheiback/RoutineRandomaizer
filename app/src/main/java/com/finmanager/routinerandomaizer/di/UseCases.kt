package com.finmanager.routinerandomaizer.di

import com.finmanager.routinerandomaizer.ReturnTask
import com.finmanager.routinerandomaizer.data.DateUtilInterface
import com.finmanager.routinerandomaizer.domain.TaskControlInterface
import com.finmanager.routinerandomaizer.domain.randomiser.RandomizerInterface
import com.finmanager.routinerandomaizer.domain.taskInterface.TaskInterface
import com.finmanager.routinerandomaizer.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class UseCases {

    @Provides
    @ViewModelScoped
    fun  provideCreateNewTaskUseCase(
        repository: TaskInterface
    ): CreateNewTaskUseCase {
        return CreateNewTaskUseCase(repository)
    }

    @Provides
    @ViewModelScoped
    fun  provideLoadTasksListUseCase(
        repository: TaskInterface
    ): LoadTasksListUseCase {
        return LoadTasksListUseCase(repository)
    }

    @Provides
    @ViewModelScoped
    fun  provideDeleteTaskUseCase(
        repository: TaskInterface
    ): DeleteTaskUseCase {
        return DeleteTaskUseCase(repository)
    }

    @Provides
    @ViewModelScoped
    fun  provideUpdateTaskUseCase(
        repository: TaskInterface
    ): UpdateTaskUseCase {
        return UpdateTaskUseCase(repository)
    }

    @Provides
    @ViewModelScoped
    fun  provideGetRandomTaskUseCase(
        repository: RandomizerInterface
    ): GetRandomTaskUseCase {
        return GetRandomTaskUseCase(repository)
    }

    @Provides
    @ViewModelScoped
    fun  provideCompleteTaskUseCase(
        repository: TaskControlInterface,
        dateUtil: DateUtilInterface
    ): CompleteTaskUseCase {
        return CompleteTaskUseCase(repository, dateUtil)
    }

    @Provides
    @ViewModelScoped
    fun  provideWakeUpUseCase(
        repository: TaskControlInterface,
    ): WakeUpUseCase {
        return WakeUpUseCase(repository)
    }

    @Provides
    @ViewModelScoped
    fun  provideGetLastTaskUseCase(
        task: TaskInterface,
        returnTask: ReturnTask
    ): GetLastTaskUseCase {
        return GetLastTaskUseCase(task, returnTask)
    }
}