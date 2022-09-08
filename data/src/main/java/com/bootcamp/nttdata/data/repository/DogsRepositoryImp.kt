package com.bootcamp.nttdata.data.repository

import com.bootcamp.nttdata.data.local.dao.DogDao
import com.bootcamp.nttdata.data.mapper.toData
import com.bootcamp.nttdata.data.mapper.toModel
import com.bootcamp.nttdata.data.service.DogsService
import com.bootcamp.nttdata.domain.repository.DogsRepository
import com.bootcamp.nttdata.models.Dogs
import com.bootcamp.nttdata.models.Failure
import com.bootcamp.nttdata.models.ResultType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DogsRepositoryImp @Inject constructor(
    private val dogsService: DogsService,
    private val dogsDao: DogDao,
) : DogsRepository {

    override suspend fun getDogsApi(): ResultType<Dogs, Failure> {
        return dogsService.getDogsForRaza()
    }

    override suspend fun getDogsDao(): ResultType<Dogs, Failure> {
        return ResultType.Success(dogsDao.getAllItemGogs().toModel())
    }

    override suspend fun insertDao(dogs: Dogs) {
        dogsDao.insertAllItemDogs(dogs.toData())
    }

    override suspend fun deleteAllDogs() {
        dogsDao.deleteAllDogs()
    }

    override suspend fun getDogsForRaza(raza: String): ResultType<Dogs, Failure> {
        return dogsService.getDogsForRaza(raza)
    }
}