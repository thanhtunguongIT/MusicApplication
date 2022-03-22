package com.tung.musicapplication.data.datasources.api

import com.tung.musicapplication.domain.EnAlbum
import retrofit2.Response
import retrofit2.http.GET

interface AlbumService {

    @GET("albums")
    suspend fun getAllAlbums(): Response<List<EnAlbum>>
}