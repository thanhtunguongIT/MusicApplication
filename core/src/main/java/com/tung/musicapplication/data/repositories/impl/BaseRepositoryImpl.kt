package com.tung.musicapplication.data.repositories.impl

import com.tung.musicapplication.dispatcher.IoDispatcher
import com.tung.musicapplication.service.safeApiCall
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Response

open class BaseRepositoryImpl(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun <T : Any> apiCall(apiCall: suspend () -> Response<T>) =
        safeApiCall(ioDispatcher, apiCall = apiCall)
}