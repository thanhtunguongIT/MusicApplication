package com.tung.musicapplication.usecase

import com.tung.musicapplication.base.TestConstants
import com.tung.musicapplication.data.repositories.RepositoryData
import com.tung.musicapplication.data.repositories.interfaces.AlbumRepository
import com.tung.musicapplication.interactors.album.GetAlbumsUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito

class GetAlbumsUseCaseTest {

    @Test
    fun testGetAlbumsUseCase() {
        runBlocking {
            val albums = TestConstants.albumData

            val mockAlbumRepository = Mockito.mock(AlbumRepository::class.java)
            Mockito.`when`(mockAlbumRepository.getAlbums()).thenReturn(albums)

            val underTest = GetAlbumsUseCase(mockAlbumRepository)
            val result = underTest.invoke()

            assert(result is RepositoryData.Success)
            assert((result as RepositoryData.Success).data.size == 1)
        }
    }
}