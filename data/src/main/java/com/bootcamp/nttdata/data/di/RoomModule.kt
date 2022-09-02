package com.bootcamp.nttdata.data.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.bootcamp.nttdata.data.local.DogsDatabase
import com.bootcamp.nttdata.data.local.IDogsDatabase
import com.bootcamp.nttdata.data.local.dao.DogDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RoomModule {

    @Provides
     fun provideDogsDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, DogsDatabase::class.java, "database_dogs")
            .build()

}

@InstallIn(SingletonComponent::class)
@Module
object DatabaseDaoModule {
    @Provides
    fun provideDogDao(db: DogsDatabase) = db.getDogsDao()

}

@InstallIn(SingletonComponent::class)
@Module
abstract class DatabaseModuleBinds {
    @Binds
    abstract fun bindDogsDatabase(db: DogsDatabase): IDogsDatabase

}
