package com.tung.musicapplication.data.repositories.impl

import com.tung.musicapplication.data.datasources.api.AlbumService
import com.tung.musicapplication.data.repositories.RepositoryData
import com.tung.musicapplication.data.repositories.interfaces.AlbumRepository
import com.tung.musicapplication.dispatcher.IoDispatcher
import com.tung.musicapplication.domain.EnAlbum
import com.tung.musicapplication.service.models.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val albumService: AlbumService
) : BaseRepositoryImpl(ioDispatcher), AlbumRepository {

    override suspend fun getAlbums(): RepositoryData<List<EnAlbum>> {
        apiCall { albumService.getAllAlbums() }.let { netWorkResult ->
            return when (netWorkResult) {
                is NetworkResult.Success -> {
                    RepositoryData.Success(netWorkResult.data)
                }
                is NetworkResult.GenericError -> {
                    RepositoryData.Failure(netWorkResult.error)
                }
                else -> RepositoryData.Failure(null)
            }
        }
    }
}