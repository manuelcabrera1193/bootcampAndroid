package com.bootcamp.nttdata.data.repository

import com.bootcamp.nttdata.data.service.DogsService
import com.bootcamp.nttdata.domain.repository.DogsRepository
import com.bootcamp.nttdata.models.Dogs
import com.bootcamp.nttdata.models.Failure
import com.bootcamp.nttdata.models.ResultType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DogsRepositoryImp @Inject constructor(private val dogsService: DogsService) : DogsRepository {

    override suspend fun getDogs(): ResultType<Dogs, Failure> {
        /**
         * si tenemos data local - > muestralo
         * en paralelo pega al servicio y traelos
         * si el servicio trae nueva información, muestralo
         */
//        val dogsService = DogsService()
        return dogsService.getDogs()
    }

    override suspend fun getDogs(raza: String) : ResultType<Dogs, Failure> {
//        val dogsService = DogsService()
        return dogsService.getDogs(raza)
    }
}