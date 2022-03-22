package com.tung.musicapplication

import android.app.Application
import com.tung.musicapplication.di.AppComponent
import com.tung.musicapplication.di.DaggerAppComponent

class MusicApplication: Application() {

    val appComponent: AppComponent = DaggerAppComponent.create()
}