package com.bootcamp.nttdata.data.service

import com.bootcamp.nttdata.data.api.DogsApi
import com.bootcamp.nttdata.data.mapper.toModel
import com.bootcamp.nttdata.data.network.NetworkManager
import com.bootcamp.nttdata.data.response.DogsResponse
import com.bootcamp.nttdata.models.Dogs
import com.bootcamp.nttdata.models.Failure
import com.bootcamp.nttdata.models.ResultType
import com.google.gson.Gson
import javax.inject.Inject
import javax.inject.Singleton

class DogsService @Inject constructor() {

    suspend fun getDogs(): ResultType<Dogs, Failure> {
        return try {
            val connection = NetworkManager.Builder()
                .baseUrl(DogsApi.BASE_URL)
                .endpoint(DogsApi.URL_LABRADOR)
                .type(NetworkManager.GET)
                .build()

            val response = connection.execute()

            if (response != null && response.isSuccessful) {
                val dogsResponse =
                    with(Gson()) {
                        fromJson(toJson(response.body()), DogsResponse::class.java)
                    }
                ResultType.Success(dogsResponse.toModel())
            } else {
                ResultType.Error(
                    Failure.ResponseInvalid(
                        response?.code() ?: 0,
                        response?.message() ?: "Error desconocido"
                    )
                )
            }
        } catch (e: Exception) {
            ResultType.Error(Failure.UnExpected)
        }
    }

    suspend fun getDogs(raza: String): ResultType<Dogs, Failure> {
        return try {
            val connection = NetworkManager.Builder()
                .baseUrl(DogsApi.BASE_URL)
                .endpoint("api/breed/$raza/images")
                .type(NetworkManager.GET)
                .build()

            val response = connection.execute()

            if (response != null && response.isSuccessful) {
                val dogsResponse =
                    with(Gson()) {
                        fromJson(toJson(response.body()), DogsResponse::class.java)
                    }
                ResultType.Success(dogsResponse.toModel())
            } else {
                ResultType.Error(
                    Failure.ResponseInvalid(
                        response?.code() ?: 0,
                        response?.message() ?: "Error desconocido"
                    )
                )
            }
        } catch (e: Exception) {
            ResultType.Error(Failure.UnExpected)
        }
    }
}