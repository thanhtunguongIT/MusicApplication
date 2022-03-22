package com.tung.musicapplication.data.repositories

import com.tung.musicapplication.service.models.DataError

sealed class RepositoryData<out T : Any> {

    data class Success<out T : Any>(val data: T) : RepositoryData<T>()

    data class Failure(val errorResponse: DataError?) : RepositoryData<Nothing>()
}