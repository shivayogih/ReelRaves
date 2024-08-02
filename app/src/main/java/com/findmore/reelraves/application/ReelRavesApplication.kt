package com.findmore.reelraves.application

import android.app.Application
import com.findmore.reelraves.network.local.TokenManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ReelRavesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        TokenManager.initialize(this)
    }
}

