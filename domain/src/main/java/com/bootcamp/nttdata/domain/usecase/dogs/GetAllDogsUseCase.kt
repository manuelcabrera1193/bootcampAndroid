package com.bootcamp.nttdata.domain.usecase.dogs

import android.util.Log
import com.bootcamp.nttdata.domain.repository.DogsRepository
import com.bootcamp.nttdata.models.Dogs
import com.bootcamp.nttdata.models.Failure
import com.bootcamp.nttdata.models.ResultType
import javax.inject.Inject
import javax.inject.Provider

class GetAllDogsUseCase @Inject constructor(private val dogsRepository: Provider<DogsRepository>) {
    suspend fun invoke(): ResultType<Dogs, Failure> {

        return when (val dogsApi = dogsRepository.get().getDogsApi()) {
            is ResultType.Error -> validateLocal()
            is ResultType.Success -> {
                Log.i("Dogs:", "Data desde ${dogsApi.value.origin}")
                if (dogsApi.value.images.isNotEmpty()) {
                    dogsRepository.get().deleteAllDogs()
                    dogsRepository.get().insertDao(dogsApi.value)
                    dogsApi
                } else {
                    dogsRepository.get().getDogsDao()
                }
            }
        }
    }

    private suspend fun validateLocal(): ResultType<Dogs, Failure> {
        return when (val dataLocal = dogsRepository.get().getDogsDao()) {
            is ResultType.Error,
            is ResultType.Success -> {
                Log.i("Dogs:", "Data desde ${dataLocal}")
                dataLocal
            }
        }
    }
}