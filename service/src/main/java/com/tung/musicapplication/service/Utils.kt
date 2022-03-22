package com.tung.musicapplication.service

import com.google.gson.Gson
import com.tung.musicapplication.service.models.DataError
import com.tung.musicapplication.service.models.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

suspend fun <T : Any> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> Response<T>
): NetworkResult<T> {
    return withContext(dispatcher) {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                if (body == null) {
                    NetworkResult.DataNull
                } else {
                    NetworkResult.Success(body)
                }
            } else {
                val code = response.code()
                NetworkResult.GenericError(
                    DataError(code, response.message())
                )
            }
        } catch (e: Exception) {
            handleError(e)
        }
    }
}

private fun <T : Any> handleError(e: Exception): NetworkResult<T> {
    return when (e) {
        is IOException -> {
            NetworkResult.NetworkError
        }
        is HttpException -> {
            val code = e.code()
            val errorResponse = convertErrorBody(e)
            NetworkResult.GenericError(
                DataError(code, errorResponse?.message())
            )
        }
        else -> {
            NetworkResult.GenericError(
                DataError(null, null)
            )
        }
    }
}

private fun convertErrorBody(throwable: HttpException): Response<*>? {
    return try {
        throwable.response()?.errorBody()?.charStream()?.let {
            Gson().fromJson(it, Response::class.java)
        }
    } catch (exception: Exception) {
        null
    }
}