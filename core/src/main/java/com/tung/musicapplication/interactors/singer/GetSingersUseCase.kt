package com.tung.musicapplication.interactors.singer

import com.tung.musicapplication.data.repositories.interfaces.SingerRepository
import javax.inject.Inject

class GetSingersUseCase @Inject constructor(
    private val singerRepository: SingerRepository
) {
    suspend operator fun invoke() = singerRepository.getSingers()
}