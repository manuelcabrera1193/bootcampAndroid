package com.bootcamp.nttdata.data.datastore

import com.bootcamp.nttdata.models.Dogs
import com.bootcamp.nttdata.models.Failure
import com.bootcamp.nttdata.models.ResultType

interface DogsDataStore {
    suspend fun getDogs()  : ResultType<Dogs, Failure>
    suspend fun getDogs(raza: String) : ResultType<Dogs, Failure>
}
