package com.bootcamp.nttdata.bootcampandroid.dogs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bootcamp.nttdata.bootcampandroid.models.ErrorClass
import com.bootcamp.nttdata.data.repository.DogsRepositoryImp
import com.bootcamp.nttdata.domain.usecase.dogs.GetAllDogsUseCase
import com.bootcamp.nttdata.domain.usecase.dogs.GetForRazaUseCase
import com.bootcamp.nttdata.models.Failure
import com.bootcamp.nttdata.models.ResultType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DogsViewModel() : ViewModel() {

    private val _dogsState = MutableLiveData<DogsState>()
    val dogsState: LiveData<DogsState> get() = _dogsState

    init {
        getDogs()
    }

    fun getDogs() {
        _dogsState.value = DogsState.Loading
        viewModelScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {
                val service = DogsRepositoryImp()
                val resultDogs = GetAllDogsUseCase(service).invoke()
                resultDogs
            }
            when (result) {
                is ResultType.Success -> {
                    _dogsState.value = DogsState.Success(result.value.images)
                }
                is ResultType.Error -> {
                    when (val error = result.value) {
                        is Failure.Http -> {
                            _dogsState.value =
                                DogsState.Error(ErrorClass(error.codeHttp, "Error Http"))
                        }
                        Failure.NetworkConnection, Failure.Unauthorized, Failure.UnExpected -> {
                            _dogsState.value =
                                DogsState.Error(ErrorClass(0, ""))
                        }
                        is Failure.ResponseInvalid -> {
                            _dogsState.value =
                                DogsState.Error(ErrorClass(error.code, error.message))
                        }
                    }

                }
            }
        }
    }

    fun getDogs(raza: String) {
        viewModelScope.launch(Dispatchers.Main){
            val result = withContext(Dispatchers.IO){
                val service = DogsRepositoryImp()
                val resultDogs = GetForRazaUseCase(service, raza).invoke()
                resultDogs
            }
            when (result){
                is ResultType.Success -> {
                    _dogsState.value = DogsState.Success(result.value.images)
                }
                is ResultType.Error -> {
                    when (val error = result.value) {
                        is Failure.Http -> {
                            _dogsState.value =
                                DogsState.Error(ErrorClass(error.codeHttp, "Error Http"))
                        }
                        Failure.NetworkConnection, Failure.Unauthorized, Failure.UnExpected -> {
                            _dogsState.value =
                                DogsState.Error(ErrorClass(0, ""))
                        }
                        is Failure.ResponseInvalid -> {
                            _dogsState.value =
                                DogsState.Error(ErrorClass(error.code, error.message))
                        }
                    }
                }
            }
        }
    }

}


