package com.bootcamp.nttdata.domain.repository

import com.bootcamp.nttdata.models.Dogs
import com.bootcamp.nttdata.models.Failure
import com.bootcamp.nttdata.models.ResultType

interface DogsRepository {

    suspend fun getDogs() : ResultType<Dogs, Failure>
    suspend fun getDogs(raza: String) : ResultType<Dogs, Failure>

}