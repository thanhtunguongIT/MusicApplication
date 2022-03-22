package com.tung.musicapplication.usecase

import com.tung.musicapplication.base.TestConstants
import com.tung.musicapplication.interactors.album.GetAlbumWithItsSingerAndPhotosUseCase
import org.junit.Test

class GetAlbumWithItsSingerAndPhotosUseCaseTest {

    @Test
    fun testGetAlbumWithItsSingerAndPhotosUseCase() {
        val underTest = GetAlbumWithItsSingerAndPhotosUseCase()
        val result = underTest.invoke(
            albumsData = TestConstants.albumData,
            photosData = TestConstants.photoData,
            singersData = TestConstants.singerData
        )

        assert(result.size == 1)
        assert(result[0].singer?.id == TestConstants.singerId)
        assert(result[0].photos?.size == 1)
        assert(result[0].photos!![0].title == TestConstants.photoName)
    }
}