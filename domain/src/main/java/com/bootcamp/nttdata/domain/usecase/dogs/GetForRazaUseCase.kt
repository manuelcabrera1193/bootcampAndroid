package com.bootcamp.nttdata.domain.usecase.dogs

import com.bootcamp.nttdata.domain.repository.DogsRepository
import com.bootcamp.nttdata.models.Dogs
import com.bootcamp.nttdata.models.Failure
import com.bootcamp.nttdata.models.ResultType

class GetForRazaUseCase (private val dogsRepository: DogsRepository,private val raza: String) {
    suspend fun invoke() : ResultType<Dogs, Failure> = dogsRepository.getDogs(raza)
}