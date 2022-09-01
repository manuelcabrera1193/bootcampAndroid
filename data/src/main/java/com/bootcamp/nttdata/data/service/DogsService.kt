package com.bootcamp.nttdata.data.service

import com.bootcamp.nttdata.data.api.DogsApi
import com.bootcamp.nttdata.data.datastore.DogsDataStore
import com.bootcamp.nttdata.data.mapper.toModel
import com.bootcamp.nttdata.data.network.NetworkManager
import com.bootcamp.nttdata.data.response.DogsResponse
import com.bootcamp.nttdata.models.Dogs
import com.bootcamp.nttdata.models.Failure
import com.bootcamp.nttdata.models.ResultType
import com.google.gson.Gson


class DogsService : DogsDataStore {

    override suspend fun getDogs(): ResultType<Dogs, Failure> {
        return try {
            val connection = NetworkManager.Builder()
                .baseUrl(DogsApi.BASE_URL)
                .endpoint(DogsApi.URL_HOUND)
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

    override fun getDogs(raza: String) {
        TODO("Not yet implemented")
    }
}