package com.findmore.reelraves.network.local

import com.findmore.reelraves.BuildConfig


object ApiConfig {
    val BaseUrl: String = BuildConfig.BaseURL
    val ImageBaseUrl: String = BuildConfig.ImageBaseURL
    val TMDB_API_KEY: String = BuildConfig.TMDB_API_KEY
    val TMDB_READ_ACCESS_TOKEN: String = BuildConfig.TMDB_ACCESS_TOKEN
}