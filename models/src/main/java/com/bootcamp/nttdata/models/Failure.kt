package com.bootcamp.nttdata.models

sealed class Failure {
    object NetworkConnection : Failure()
    data class Http(val codeHttp: Int) : Failure()
    object UnExpected : Failure()
    data class Local(val data: Any) : Failure()
    data class ResponseInvalid(val code: Int,val message: String) : Failure()
    object Unauthorized : Failure()
}