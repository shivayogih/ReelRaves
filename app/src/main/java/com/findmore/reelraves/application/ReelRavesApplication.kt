package com.findmore.reelraves.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class ReelRavesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}