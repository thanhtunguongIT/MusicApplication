package com.tung.musicapplication.music

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tung.musicapplication.base.MainCoroutineRule
import com.tung.musicapplication.base.TestConstants
import com.tung.musicapplication.data.repositories.interfaces.AlbumRepository
import com.tung.musicapplication.data.repositories.interfaces.PhotoRepository
import com.tung.musicapplication.data.repositories.interfaces.SingerRepository
import com.tung.musicapplication.interactors.album.GetAlbumWithItsSingerAndPhotosUseCase
import com.tung.musicapplication.interactors.album.GetAlbumsUseCase
import com.tung.musicapplication.interactors.album.SearchAlbumBySingerName
import com.tung.musicapplication.interactors.photo.GetPhotosUseCase
import com.tung.musicapplication.interactors.singer.GetSingersUseCase
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.stub

@RunWith(MockitoJUnitRunner::class)
class MusicViewModelTest : TestCase() {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @get:Rule var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var underTest: MusicViewModel

    @Before
    fun setup() {

        val mockAlbumRepository = Mockito.mock(AlbumRepository::class.java)
        mockAlbumRepository.stub {
            onBlocking { getAlbums() }.doReturn(TestConstants.albumData)
        }

        val getAlbumsUseCase = GetAlbumsUseCase(mockAlbumRepository)

        val mockSingerRepository = Mockito.mock(SingerRepository::class.java)
        mockSingerRepository.stub {
            onBlocking { getSingers() }.doReturn(TestConstants.singerData)
        }

        val getSingersUseCase = GetSingersUseCase(mockSingerRepository)

        val mockPhotoRepository = Mockito.mock(PhotoRepository::class.java)
        mockPhotoRepository.stub {
            onBlocking { getPhotos() }.doReturn(TestConstants.photoData)
        }

        val getPhotosUseCase = GetPhotosUseCase(mockPhotoRepository)

        val getAlbumWithItsSingerAndPhotosUseCase = GetAlbumWithItsSingerAndPhotosUseCase()

        val searchAlbumBySingerName = SearchAlbumBySingerName()

        underTest = MusicViewModel(
            getAlbumsUseCase = getAlbumsUseCase,
            getSingersUseCase = getSingersUseCase,
            getPhotosUseCase = getPhotosUseCase,
            getAlbumWithItsSingerAndPhotosUseCase = getAlbumWithItsSingerAndPhotosUseCase,
            searchAlbumBySingerName = searchAlbumBySingerName
        )
    }

    @Test
    fun testFetchAlbums() {
        underTest.fetchAlbums()
        val result = underTest.getAlbumsLiveData().getOrAwaitValue()
        assert(result.size == 1)
    }

    @Test
    fun testSearchAlbumBySinger() {
        underTest.fetchAlbums()
        underTest.searchAlbumBySinger(TestConstants.singerName)
        val result = underTest.getAlbumsLiveData().getOrAwaitValue()
        assert(result.size == 1)
        assert(result[0].singer?.name?.equals(TestConstants.singerName) == true)
    }
}