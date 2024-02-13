package com.geeks.rickandmorty

import android.app.Application
import com.geeks.rickandmorty.di.networkModule
import com.geeks.rickandmorty.di.repositoryModule
import com.geeks.rickandmorty.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(viewModelModule, networkModule, repositoryModule)
        }
    }

}