package com.findmore.reelraves.data.repository

import android.util.Log
import com.findmore.reelraves.common.Resource
import com.findmore.reelraves.data.dao.MovieDao
import com.findmore.reelraves.data.model.MovieGenres
import com.findmore.reelraves.data.model.MovieDetailsResponse
import com.findmore.reelraves.data.model.MoviesList
import com.findmore.reelraves.network.RetrofitService
import com.findmore.reelraves.network.local.ApiConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieDao: MovieDao,
    private val retrofitService: RetrofitService
) : MovieRepository {

    override suspend fun getMoviesList(queryMap: MutableMap<String, Any>): Resource<MoviesList> {
        try {
            return Resource.Success(retrofitService.getMoviesList(queryMap))
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred: ${e.localizedMessage}")
        }
    }

    override suspend fun getMovieDetails(movieId: Int): Resource<MovieDetailsResponse> {
        try {
            return Resource.Success(
                retrofitService.getMovieDetails(
                    movieId
                )
            )
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred: ${e.localizedMessage}")
        }
    }


}