package com.tung.musicapplication.data.repositories.interfaces

import com.tung.musicapplication.data.repositories.RepositoryData
import com.tung.musicapplication.domain.EnPhoto

interface PhotoRepository {

    suspend fun getPhotos(): RepositoryData<List<EnPhoto>>
}