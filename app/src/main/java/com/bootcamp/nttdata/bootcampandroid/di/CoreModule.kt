package com.bootcamp.nttdata.bootcampandroid.di

import com.bootcamp.nttdata.data.repository.DogsRepositoryImp
import com.bootcamp.nttdata.domain.repository.DogsRepository
import com.bootcamp.nttdata.domain.usecase.dogs.GetAllDogsUseCase
import com.bootcamp.nttdata.domain.usecase.dogs.GetForRazaUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module(includes = [CoreModule.BindsModule::class])
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    fun provideGetAllDogsUseCase(
        repository: DogsRepository
    ): GetAllDogsUseCase = GetAllDogsUseCase(repository)

    @Provides
    fun provideGetForRazaUseCase(
        repository: DogsRepository
    ): GetForRazaUseCase = GetForRazaUseCase(repository)

    @Module
    @InstallIn(SingletonComponent::class)
    interface BindsModule {

        @Binds
        @Singleton
        fun provideDogsRepository(
            myRepositoryImpl: DogsRepositoryImp
        ): DogsRepository

    }

}
