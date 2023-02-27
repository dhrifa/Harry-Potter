package com.example.harrypotterstudents.di.koin

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class HarryPotterApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@HarryPotterApp)
            modules(listOf(
                networkModule,
                repoModule,
                viewModelModule
            ))
        }
    }
}