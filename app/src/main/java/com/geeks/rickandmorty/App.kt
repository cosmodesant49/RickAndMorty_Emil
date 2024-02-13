package com.geeks.rickandmorty

import android.app.Application
import com.geeks.rickandmorty.di.cartoonModule
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(cartoonModule)
        }
    }

}