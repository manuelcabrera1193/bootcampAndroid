package com.bootcamp.nttdata.bootcampandroid.di

import com.bootcamp.nttdata.data.repository.DogsRepositoryImp
import com.bootcamp.nttdata.domain.repository.DogsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class CoreModule {

    @Binds
    abstract fun provideDogsRepository(
        myRepositoryImpl: DogsRepositoryImp
    ): DogsRepository

}