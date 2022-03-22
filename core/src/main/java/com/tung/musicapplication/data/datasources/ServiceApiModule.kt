package com.tung.musicapplication.data.datasources

import com.tung.musicapplication.data.datasources.api.AlbumService
import com.tung.musicapplication.data.datasources.api.PhotoService
import com.tung.musicapplication.data.datasources.api.SingerService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ServiceApiModule {

    @Provides
    @Singleton
    fun provideAlbumApi(retrofit: Retrofit): AlbumService {
        return retrofit.create(AlbumService::class.java)
    }

    @Provides
    @Singleton
    fun providePhotoApi(retrofit: Retrofit): PhotoService {
        return retrofit.create(PhotoService::class.java)
    }

    @Provides
    @Singleton
    fun provideSingerApi(retrofit: Retrofit): SingerService {
        return retrofit.create(SingerService::class.java)
    }
}