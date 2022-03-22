package com.tung.musicapplication.data.repositories.impl

import com.tung.musicapplication.data.datasources.api.PhotoService
import com.tung.musicapplication.data.repositories.RepositoryData
import com.tung.musicapplication.data.repositories.interfaces.PhotoRepository
import com.tung.musicapplication.dispatcher.IoDispatcher
import com.tung.musicapplication.domain.EnPhoto
import com.tung.musicapplication.service.models.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher

class PhotoRepositoryImpl(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val photoService: PhotoService
) : BaseRepositoryImpl(ioDispatcher), PhotoRepository {

    override suspend fun getPhotos(): RepositoryData<List<EnPhoto>> {
        apiCall { photoService.getAllPhotos() }.let { netWorkResult ->
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