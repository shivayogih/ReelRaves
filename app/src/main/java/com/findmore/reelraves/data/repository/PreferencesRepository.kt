package com.findmore.reelraves.data.repository


interface  PreferencesRepository {
    fun isFirstLaunch(): Boolean
    fun setFirstLaunch(isFirstLaunch: Boolean)
}