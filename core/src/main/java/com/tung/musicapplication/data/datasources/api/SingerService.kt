package com.tung.musicapplication.data.datasources.api

import com.tung.musicapplication.domain.EnSinger
import retrofit2.Response
import retrofit2.http.GET

interface SingerService {

    @GET("users")
    suspend fun getAllUsers(): Response<List<EnSinger>>
}