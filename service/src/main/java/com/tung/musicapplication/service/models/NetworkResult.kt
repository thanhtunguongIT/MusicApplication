package com.tung.musicapplication.service.models

sealed class NetworkResult<out T : Any> {

    data class Success<out T: Any>(val data: T) : NetworkResult<T>()

    object DataNull: NetworkResult<Nothing>()

    data class GenericError(val error: DataError? = null) :
        NetworkResult<Nothing>()

    object NetworkError : NetworkResult<Nothing>()
}

data class DataError(val code: Int?, val message: String?)
