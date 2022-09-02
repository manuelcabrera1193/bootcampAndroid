package com.bootcamp.nttdata.data.repository

import android.content.Context
import androidx.room.Room
import com.bootcamp.nttdata.data.local.DogsDatabase
import com.bootcamp.nttdata.data.local.dao.DogDao
import com.bootcamp.nttdata.data.mapper.toData
import com.bootcamp.nttdata.data.mapper.toModel
import com.bootcamp.nttdata.data.service.DogsService
import com.bootcamp.nttdata.domain.repository.DogsRepository
import com.bootcamp.nttdata.models.Dogs
import com.bootcamp.nttdata.models.Failure
import com.bootcamp.nttdata.models.ResultType
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Provider

class DogsRepositoryImp @Inject constructor(
    private val dogsService: Provider<DogsService>,
    @ApplicationContext private val context: Context,
//    private val dogsDao: Provider<DogDao>,
) : DogsRepository {

    val db = Room.databaseBuilder(context, DogsDatabase::class.java, "database_dogs")
        .build()

    override suspend fun getDogsApi(): ResultType<Dogs, Failure> {
        return dogsService.get().getDogs()
//        return ResultType.Success(dogsService.get().getAllItemGogs().toModel())
    }

    override suspend fun getDogsDao(): ResultType<Dogs, Failure> {

        return ResultType.Success(db.getDogsDao().getAllItemGogs().toModel())
//        ResultType.Success(dogDao.getAllItemGogs().toModel())
    }

    override suspend fun insertDao(dogs: Dogs) {
        db.getDogsDao().insertAllItemDogs(dogs.toData())
    }

    override suspend fun deleteAllDogs() {
        db.getDogsDao().deleteAllDogs()
    }

    override suspend fun getDogs(raza: String): ResultType<Dogs, Failure> {
//        return ResultType.Success(dogsService.get().getAllItemGogs().toModel())
            return dogsService.get().getDogs(raza)
    }
}