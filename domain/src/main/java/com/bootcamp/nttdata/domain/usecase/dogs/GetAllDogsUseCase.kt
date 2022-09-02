package com.bootcamp.nttdata.domain.usecase.dogs

import com.bootcamp.nttdata.domain.repository.DogsRepository
import com.bootcamp.nttdata.models.Dogs
import com.bootcamp.nttdata.models.Failure
import com.bootcamp.nttdata.models.ResultType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAllDogsUseCase @Inject constructor(private val dogsRepository: DogsRepository) {
    suspend fun invoke(): ResultType<Dogs, Failure> = dogsRepository.getDogs()
}