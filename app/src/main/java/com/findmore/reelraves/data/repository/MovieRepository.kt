package com.findmore.reelraves.data.repository


import com.findmore.reelraves.common.Resource
import com.findmore.reelraves.data.model.MovieGenres
import com.findmore.reelraves.data.model.MovieDetailsResponse
import com.findmore.reelraves.data.model.MoviesList


interface MovieRepository {

    suspend fun getMoviesList(queryMap: MutableMap<String, Any>): Resource<MoviesList>
    suspend fun getMovieDetails(movieId: Int): Resource<MovieDetailsResponse>
}