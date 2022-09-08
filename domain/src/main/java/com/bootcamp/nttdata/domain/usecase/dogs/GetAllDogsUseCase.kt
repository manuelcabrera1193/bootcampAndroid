package com.bootcamp.nttdata.domain.usecase.dogs

import android.util.Log
import com.bootcamp.nttdata.domain.repository.DogsRepository
import com.bootcamp.nttdata.models.Dogs
import com.bootcamp.nttdata.models.Failure
import com.bootcamp.nttdata.models.ResultType
import javax.inject.Inject
import javax.inject.Provider

class GetAllDogsUseCase @Inject constructor(private val dogsRepository: DogsRepository) {
    suspend operator fun invoke(): ResultType<Dogs, Failure> {

        return when (val dogsApi = dogsRepository.getDogsApi()) {
            is ResultType.Error -> validateLocal()
            is ResultType.Success -> {
                Log.i("Dogs:", "Data desde ${dogsApi.value.origin}")
                if (dogsApi.value.images.isNotEmpty()) {
                    dogsRepository.deleteAllDogs()
                    dogsRepository.insertDao(dogsApi.value)
                    dogsApi
                } else {
                    dogsRepository.getDogsDao()
                }
            }
        }
    }

    private suspend fun validateLocal(): ResultType<Dogs, Failure> {
        return when (val dataLocal = dogsRepository.getDogsDao()) {
            is ResultType.Error,
            is ResultType.Success -> {
                Log.i("Dogs:", "Data desde ${dataLocal}")
                dataLocal
            }
        }
    }
}