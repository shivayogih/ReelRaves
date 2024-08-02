package com.findmore.reelraves.network.local

import android.content.Context
import android.content.SharedPreferences
import com.findmore.reelraves.network.local.ApiConfig.TMDB_READ_ACCESS_TOKEN

object TokenManager {

    private const val PREF_NAME = "token_prefs"
    const val TOKEN_KEY = "bearer_token"
    val TOKEN_VALUE = TMDB_READ_ACCESS_TOKEN
    private lateinit var sharedPreferences: SharedPreferences

    fun initialize(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        saveToken(TOKEN_VALUE)
    }

    fun saveToken(token: String) {
        checkInitialization()
        val editor = sharedPreferences.edit()
        editor.putString(TOKEN_KEY, token)
        editor.apply()
    }

    fun getToken(): String? {
        checkInitialization()
        return sharedPreferences.getString(TOKEN_KEY, TOKEN_VALUE)
    }

    fun clearToken() {
        checkInitialization()
        val editor = sharedPreferences.edit()
        editor.remove(TOKEN_KEY)
        editor.apply()
    }

    private fun checkInitialization() {
        if (!::sharedPreferences.isInitialized) {
            throw IllegalStateException("TokenManager is not initialized. Call initialize() with a context before using.")
        }
    }
}
