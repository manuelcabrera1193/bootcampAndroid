package com.bootcamp.nttdata.models

sealed class Failure {
    object NetworkConnection : Failure()
    object Http : Failure()
    object UnExpected : Failure()
    object ResponseInvalid : Failure()
    object Unauthorized : Failure()
}