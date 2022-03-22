package com.tung.musicapplication.usecase

import com.tung.musicapplication.base.TestConstants
import com.tung.musicapplication.data.repositories.RepositoryData
import com.tung.musicapplication.data.repositories.interfaces.SingerRepository
import com.tung.musicapplication.interactors.singer.GetSingersUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito

class GetSingersUseCaseTest {

    @Test
    fun testGetSingersUseCase() {
        runBlocking {
            val singers = TestConstants.singerData

            val mockSingerRepository = Mockito.mock(SingerRepository::class.java)
            Mockito.`when`(mockSingerRepository.getSingers()).thenReturn(singers)

            val underTest = GetSingersUseCase(mockSingerRepository)
            val result = underTest.invoke()

            assert(result is RepositoryData.Success)
            assert((result as RepositoryData.Success).data.size == 1)
        }
    }
}