package com.tung.musicapplication.usecase;

import com.tung.musicapplication.base.TestConstants
import com.tung.musicapplication.data.repositories.RepositoryData
import com.tung.musicapplication.data.repositories.interfaces.PhotoRepository
import com.tung.musicapplication.interactors.photo.GetPhotosUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito

class GetPhotosUseCaseTest {

    @Test
    fun testGetPhotosUseCase() {
        runBlocking {
            val photos = TestConstants.photoData

            val mockPhotoRepository = Mockito.mock(PhotoRepository::class.java)
            Mockito.`when`(mockPhotoRepository.getPhotos()).thenReturn(photos)

            val underTest = GetPhotosUseCase(mockPhotoRepository)
            val result = underTest.invoke()

            assert(result is RepositoryData.Success)
            assert((result as RepositoryData.Success).data.size == 1)
        }
    }
}
