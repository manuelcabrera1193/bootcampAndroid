package com.bootcamp.nttdata.bootcampandroid.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bootcamp.nttdata.bootcampandroid.models.ErrorClass
import com.bootcamp.nttdata.bootcampandroid.models.Person
import com.bootcamp.nttdata.bootcampandroid.states.PersonState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {

    private val _personState = MutableLiveData<PersonState>()
    val personState: LiveData<PersonState> get() = _personState

    companion object {
        const val TAG = "DetailViewModel"
    }

    fun validateSession() {
        Log.i(TAG, "validateSession")
    }

    fun validateCredentials(user: String, pass: String) {
        _personState.value = PersonState.Loading
        viewModelScope.launch {
            delay(3000)
            if (user == "Manuel" && pass == "123") {
                val responseService = Person(1, "Manuel", "Cabrera")
                _personState.value = PersonState.Success(responseService)
            } else {
                _personState.value = PersonState.Error(ErrorClass(401, "Recurso de token"))
            }
        }
    }

}