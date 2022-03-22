package com.tung.musicapplication.interactors.album

import com.tung.musicapplication.data.repositories.interfaces.AlbumRepository
import javax.inject.Inject

class GetAlbumsUseCase @Inject constructor(
    private val albumRepository: AlbumRepository
) {
    suspend operator fun invoke() = albumRepository.getAlbums()
}