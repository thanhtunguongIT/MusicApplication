package com.tung.musicapplication.interactors.photo

import com.tung.musicapplication.data.repositories.interfaces.PhotoRepository
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(
    private val photoRepository: PhotoRepository
) {
    suspend operator fun invoke() = photoRepository.getPhotos()
}