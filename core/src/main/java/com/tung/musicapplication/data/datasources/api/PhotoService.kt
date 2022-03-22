package com.tung.musicapplication.data.datasources.api

import com.tung.musicapplication.domain.EnPhoto
import retrofit2.Response
import retrofit2.http.GET

interface PhotoService {

    @GET("photos")
    suspend fun getAllPhotos(): Response<List<EnPhoto>>
}