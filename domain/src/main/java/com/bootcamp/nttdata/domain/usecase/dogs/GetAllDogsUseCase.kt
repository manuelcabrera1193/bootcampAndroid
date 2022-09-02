package com.bootcamp.nttdata.domain.usecase.dogs

import android.util.Log
import com.bootcamp.nttdata.domain.repository.DogsRepository
import com.bootcamp.nttdata.models.Dogs
import com.bootcamp.nttdata.models.Failure
import com.bootcamp.nttdata.models.ResultType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAllDogsUseCase @Inject constructor(private val dogsRepository: DogsRepository, private val dogs: Dogs) {
    suspend fun invoke(): ResultType<Dogs, Failure> {
        Log.i("dogs::", dogs.toString())
        return dogsRepository.getDogs()
    }
}