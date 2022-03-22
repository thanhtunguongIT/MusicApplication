package com.tung.musicapplication.data.repositories.interfaces

import com.tung.musicapplication.data.repositories.RepositoryData
import com.tung.musicapplication.domain.EnAlbum

interface AlbumRepository {

    suspend fun getAlbums(): RepositoryData<List<EnAlbum>>
}