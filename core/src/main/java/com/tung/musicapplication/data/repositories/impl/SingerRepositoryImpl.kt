package com.tung.musicapplication.data.repositories.impl

import com.tung.musicapplication.data.repositories.interfaces.SingerRepository
import com.tung.musicapplication.dispatcher.IoDispatcher
import com.tung.musicapplication.domain.EnSinger
import com.tung.musicapplication.data.datasources.api.SingerService
import com.tung.musicapplication.data.repositories.RepositoryData
import com.tung.musicapplication.service.models.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher

class SingerRepositoryImpl(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val singerService: SingerService
) : BaseRepositoryImpl(ioDispatcher), SingerRepository {

    override suspend fun getSingers(): RepositoryData<List<EnSinger>> {
        apiCall { singerService.getAllUsers() }.let { netWorkResult ->
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