package com.findmore.reelraves.data.repository

import android.content.SharedPreferences
import javax.inject.Inject

class PreferencesRepositoryImpl @Inject constructor(
    private val preferences: SharedPreferences
) : PreferencesRepository{

    override fun isFirstLaunch(): Boolean {
        return preferences.getBoolean("isFirstLaunch", true)
    }

    override fun setFirstLaunch(isFirstLaunch: Boolean) {
        preferences.edit().putBoolean("isFirstLaunch", !isFirstLaunch).apply()
    }

}