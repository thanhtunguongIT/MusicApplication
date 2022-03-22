package com.tung.musicapplication.interactors.di

import com.tung.musicapplication.data.repositories.interfaces.AlbumRepository
import com.tung.musicapplication.data.repositories.interfaces.PhotoRepository
import com.tung.musicapplication.data.repositories.interfaces.SingerRepository
import com.tung.musicapplication.interactors.album.GetAlbumWithItsSingerAndPhotosUseCase
import com.tung.musicapplication.interactors.album.GetAlbumsUseCase
import com.tung.musicapplication.interactors.album.SearchAlbumBySingerName
import com.tung.musicapplication.interactors.photo.GetPhotosUseCase
import com.tung.musicapplication.interactors.singer.GetSingersUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UserCaseModule {

    @Provides
    @Singleton
    fun provideGetAlbumWithItsSingerAndPhotosUseCase() = GetAlbumWithItsSingerAndPhotosUseCase()

    @Provides
    @Singleton
    fun provideGetAlbums(albumRepository: AlbumRepository) = GetAlbumsUseCase(albumRepository)

    @Provides
    @Singleton
    fun provideGetPhotos(photoRepository: PhotoRepository) = GetPhotosUseCase(photoRepository)

    @Provides
    @Singleton
    fun provideGetSingers(singerRepository: SingerRepository) = GetSingersUseCase(singerRepository)

    @Provides
    fun searchAlbumBySinger() = SearchAlbumBySingerName()
}