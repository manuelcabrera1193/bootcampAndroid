package com.bootcamp.nttdata.domain.repository

import com.bootcamp.nttdata.models.Dogs
import com.bootcamp.nttdata.models.Failure
import com.bootcamp.nttdata.models.ResultType

interface DogsRepository {

    suspend fun getDogsApi() : ResultType<Dogs, Failure>
    suspend fun getDogsDao() : ResultType<Dogs, Failure>
    suspend fun insertDao(dogs: Dogs)
    suspend fun deleteAllDogs()
    suspend fun getDogs(raza: String) : ResultType<Dogs, Failure>

}