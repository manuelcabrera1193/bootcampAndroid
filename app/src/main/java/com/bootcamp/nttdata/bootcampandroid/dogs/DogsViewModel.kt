package com.bootcamp.nttdata.bootcampandroid.dogs

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bootcamp.nttdata.bootcampandroid.models.ErrorClass
import com.bootcamp.nttdata.network.NetworkManager
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DogsViewModel : ViewModel() {


    private val _dogsState = MutableLiveData<DogsState>()
    val dogsState: LiveData<DogsState> get() = _dogsState

    init {
        getMovies()
    }


    private fun getMovies() {
        _dogsState.value = DogsState.Loading
        viewModelScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {
                NetworkManager.Builder()
                    .baseUrl(BASE_URL)
                    .endpoint(URL_DOGS)
                    .type(NetworkManager.GET)
                    .build()
                    .execute()
            }

            Log.i("Movies:", result?.body().toString())

            if (result?.isSuccessful == true) {
                val detailResponse = with(Gson()) {
                    this.fromJson(this.toJson(result.body()), DogsResponse::class.java)
                }
                _dogsState.value = DogsState.Success(detailResponse.message)

            } else {
                _dogsState.value = DogsState.Error(
                    ErrorClass(
                        result?.code() ?: 0,
                        result?.message() ?: "Error desconocido"
                    )
                )
            }
        }
    }

    companion object{

        const val BASE_URL = "https://dog.ceo/"
        const val URL_DOGS = "api/breed/hound/images"

    }

}


