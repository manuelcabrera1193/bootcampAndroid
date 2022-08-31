package com.bootcamp.nttdata.bootcampandroid.dogs

import com.bootcamp.nttdata.bootcampandroid.models.ErrorClass

sealed class DogsState {
    object Loading : DogsState()
    data class Success(val dogs: List<String>?) : DogsState()
    data class Error(val error: ErrorClass) : DogsState()
}