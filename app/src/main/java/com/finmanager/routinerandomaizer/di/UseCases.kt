package com.finmanager.routinerandomaizer.di

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
}