package com.bootcamp.nttdata.bootcampandroid.di

import android.content.Context
import androidx.room.Room
import com.bootcamp.nttdata.data.local.DogsDatabase
import com.bootcamp.nttdata.data.local.dao.DogDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val APP_DATABASE_NAME = "database_dogs"

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): DogsDatabase {
        return Room.databaseBuilder(
            context,
            DogsDatabase::class.java,
            APP_DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideRocketDao(database: DogsDatabase): DogDao {
        return database.getDogsDao()
    }
}