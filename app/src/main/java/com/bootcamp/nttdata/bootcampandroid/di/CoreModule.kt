package com.bootcamp.nttdata.bootcampandroid.di

import com.bootcamp.nttdata.data.repository.DogsRepositoryImp
import com.bootcamp.nttdata.domain.repository.DogsRepository
import com.bootcamp.nttdata.models.Dogs
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class CoreModule {

    @Binds
    @Singleton
    abstract fun provideDogsRepository(
        myRepositoryImpl: DogsRepositoryImp
    ): DogsRepository

}


@Module
@InstallIn(SingletonComponent::class)
class IDogs {

    @Provides
    @Singleton
    fun providerGetDogs(): Dogs {
        val list = mutableListOf<String>("doberman", "pitbull")
        return Dogs(images = list)
    }

}