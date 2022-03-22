package com.tung.musicapplication.data.repositories.interfaces

import com.tung.musicapplication.data.repositories.RepositoryData
import com.tung.musicapplication.domain.EnSinger

interface SingerRepository {

    suspend fun getSingers(): RepositoryData<List<EnSinger>>
}