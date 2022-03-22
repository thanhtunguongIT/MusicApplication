package com.tung.musicapplication.repositories

import com.tung.musicapplication.base.BaseNetworkTest
import com.tung.musicapplication.base.TestConstants
import com.tung.musicapplication.base.configureNetworkModuleForTest
import com.tung.musicapplication.data.datasources.api.PhotoService
import com.tung.musicapplication.data.repositories.RepositoryData
import com.tung.musicapplication.data.repositories.impl.PhotoRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.net.HttpURLConnection

@RunWith(MockitoJUnitRunner::class)
class PhotoRepositoryImplTest : BaseNetworkTest() {

    private lateinit var photoService: PhotoService
    private lateinit var underTest: PhotoRepositoryImpl

    @Before
    override fun setUp() {
        super.setUp()

        photoService = configureNetworkModuleForTest(networkTest.getMockWebServerUrl()).create(
            PhotoService::class.java
        )

        underTest = PhotoRepositoryImpl(
            ioDispatcher = Dispatchers.IO,
            photoService
        )
    }

    @Test
    fun testGetSingersHttpOkay() {
        runBlocking {
            networkTest.mockNetworkResponseWithFileContent(
                TestConstants.PHOTOS_JSON_RESPONSE,
                HttpURLConnection.HTTP_OK
            )
            val result = underTest.getPhotos()
            assert(result is RepositoryData.Success)
            assert((result as RepositoryData.Success).data.size == TestConstants.PHOTOS_RESPONSE_SIZE)
        }
    }

    @Test
    fun testGetAlbumsHttpNotFound() {
        runBlocking {
            networkTest.mockNetworkResponseWithFileContent(
                TestConstants.SINGERS_JSON_RESPONSE,
                HttpURLConnection.HTTP_NOT_FOUND
            )
            val result = underTest.getPhotos()
            assert(result is RepositoryData.Failure)
            assert((result as RepositoryData.Failure).errorResponse?.code == 404)
        }
    }

    @After
    override fun tearDown() {
        super.tearDown()
    }
}