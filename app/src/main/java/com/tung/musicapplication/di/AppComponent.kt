package com.tung.musicapplication.di

import com.tung.musicapplication.AppModule
import com.tung.musicapplication.MusicApplication
import com.tung.musicapplication.data.datasources.ServiceApiModule
import com.tung.musicapplication.data.repositories.RepositoryModule
import com.tung.musicapplication.di.viewmodel.ViewModelModule
import com.tung.musicapplication.dispatcher.DispatcherModule
import com.tung.musicapplication.interactors.di.UserCaseModule
import com.tung.musicapplication.music.AlbumsFragment
import com.tung.musicapplication.service.di.RetrofitModule
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ViewModelModule::class,
        DispatcherModule::class,
        UserCaseModule::class,
        RepositoryModule::class,
        ServiceApiModule::class,
        RetrofitModule::class
    ]
)
interface AppComponent: AndroidInjector<MusicApplication> {

    fun inject(albumsFragment: AlbumsFragment)
}