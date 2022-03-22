package com.tung.musicapplication.repositories

import com.tung.musicapplication.base.BaseNetworkTest
import com.tung.musicapplication.base.TestConstants
import com.tung.musicapplication.base.configureNetworkModuleForTest
import com.tung.musicapplication.data.datasources.api.SingerService
import com.tung.musicapplication.data.repositories.RepositoryData
import com.tung.musicapplication.data.repositories.impl.SingerRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.net.HttpURLConnection

@RunWith(MockitoJUnitRunner::class)
class SingerRepositoryImplTest : BaseNetworkTest() {

    private lateinit var singerService: SingerService
    private lateinit var underTest: SingerRepositoryImpl

    @Before
    override fun setUp() {
        super.setUp()

        singerService = configureNetworkModuleForTest(networkTest.getMockWebServerUrl()).create(
            SingerService::class.java
        )

        underTest = SingerRepositoryImpl(
            ioDispatcher = Dispatchers.IO,
            singerService
        )
    }

    @Test
    fun testGetSingersHttpOkay() {
        runBlocking {
            networkTest.mockNetworkResponseWithFileContent(
                TestConstants.SINGERS_JSON_RESPONSE,
                HttpURLConnection.HTTP_OK
            )
            val result = underTest.getSingers()
            assert(result is RepositoryData.Success)
            assert((result as RepositoryData.Success).data.size == TestConstants.SINGER_RESPONSE_SIZE)
        }
    }

    @Test
    fun testGetAlbumsHttpNotFound() {
        runBlocking {
            networkTest.mockNetworkResponseWithFileContent(
                TestConstants.SINGERS_JSON_RESPONSE,
                HttpURLConnection.HTTP_NOT_FOUND
            )
            val result = underTest.getSingers()
            assert(result is RepositoryData.Failure)
            assert((result as RepositoryData.Failure).errorResponse?.code == 404)
        }
    }

    @After
    override fun tearDown() {
        super.tearDown()
    }
}