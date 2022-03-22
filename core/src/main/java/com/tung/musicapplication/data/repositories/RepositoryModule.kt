package com.tung.musicapplication.data.repositories

import com.tung.musicapplication.data.datasources.api.AlbumService
import com.tung.musicapplication.data.repositories.impl.AlbumRepositoryImpl
import com.tung.musicapplication.data.repositories.impl.PhotoRepositoryImpl
import com.tung.musicapplication.data.repositories.impl.SingerRepositoryImpl
import com.tung.musicapplication.data.repositories.interfaces.AlbumRepository
import com.tung.musicapplication.data.repositories.interfaces.PhotoRepository
import com.tung.musicapplication.data.repositories.interfaces.SingerRepository
import com.tung.musicapplication.dispatcher.IoDispatcher
import com.tung.musicapplication.data.datasources.api.PhotoService
import com.tung.musicapplication.data.datasources.api.SingerService
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideAlbumRepository(
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
        albumService: AlbumService
    ): AlbumRepository {
        return AlbumRepositoryImpl(ioDispatcher, albumService)
    }

    @Provides
    @Singleton
    fun providePhotoRepository(
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
        photoService: PhotoService
    ): PhotoRepository {
        return PhotoRepositoryImpl(ioDispatcher, photoService)
    }

    @Provides
    @Singleton
    fun provideSingerRepository(
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
        singerService: SingerService
    ): SingerRepository {
        return SingerRepositoryImpl(ioDispatcher, singerService)
    }
}