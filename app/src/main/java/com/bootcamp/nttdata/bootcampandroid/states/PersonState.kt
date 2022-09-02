package com.bootcamp.nttdata.bootcampandroid.states

import com.bootcamp.nttdata.bootcampandroid.models.ErrorClass
import com.bootcamp.nttdata.bootcampandroid.models.Person

sealed class PersonState {
    object Loading : PersonState()
    data class Success(val person: Person) : PersonState()
    data class Error(val error: ErrorClass) : PersonState()
}
