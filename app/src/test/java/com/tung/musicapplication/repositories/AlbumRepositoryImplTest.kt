package com.tung.musicapplication.repositories

import com.tung.musicapplication.base.BaseNetworkTest
import com.tung.musicapplication.base.TestConstants
import com.tung.musicapplication.base.configureNetworkModuleForTest
import com.tung.musicapplication.data.datasources.api.AlbumService
import com.tung.musicapplication.data.repositories.RepositoryData
import com.tung.musicapplication.data.repositories.impl.AlbumRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.net.HttpURLConnection

@RunWith(MockitoJUnitRunner::class)
class AlbumRepositoryImplTest : BaseNetworkTest() {

    private lateinit var albumService: AlbumService
    private lateinit var underTest: AlbumRepositoryImpl

    @Before
    override fun setUp() {
        super.setUp()

        albumService = configureNetworkModuleForTest(networkTest.getMockWebServerUrl()).create(
            AlbumService::class.java
        )

        underTest = AlbumRepositoryImpl(
            ioDispatcher = Dispatchers.IO,
            albumService
        )
    }

    @Test
    fun testGetAlbumsHttpOkay() {
        runBlocking {
            networkTest.mockNetworkResponseWithFileContent(
                TestConstants.ALBUMS_JSON_RESPONSE,
                HttpURLConnection.HTTP_OK
            )
            val result = underTest.getAlbums()
            assert(result is RepositoryData.Success)
            assert((result as RepositoryData.Success).data.size == TestConstants.ALBUMS_RESPONSE_SIZE)
        }
    }

    @Test
    fun testGetAlbumsHttpNotFound() {
        runBlocking {
            networkTest.mockNetworkResponseWithFileContent(
                TestConstants.ALBUMS_JSON_RESPONSE,
                HttpURLConnection.HTTP_NOT_FOUND
            )
            val result = underTest.getAlbums()
            assert(result is RepositoryData.Failure)
            assert((result as RepositoryData.Failure).errorResponse?.code == HttpURLConnection.HTTP_NOT_FOUND)
        }
    }

    @After
    override fun tearDown() {
        super.tearDown()
    }
}