package com.findmore.reelraves.network


import com.findmore.reelraves.data.model.GenreResponse
import com.findmore.reelraves.data.model.MovieDetailsResponse
import com.findmore.reelraves.data.model.MovieResponse
import com.findmore.reelraves.data.model.MoviesList
import com.findmore.reelraves.data.model.TvSeriesDetails
import com.findmore.reelraves.data.model.TvSeriesListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface RetrofitService {

    @GET("genre/movie/list")
    suspend fun getTVGenres(@Query("api_key") apiKey: String): GenreResponse

    @GET("movie/popular")
    suspend fun getMoviesGenres(@Query("api_key") apiKey: String): MovieResponse


    @GET("discover/movie")
    @JvmSuppressWildcards
    suspend fun getMoviesList(@QueryMap options: Map<String, Any>): MoviesList

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int
    ): MovieDetailsResponse


    @GET("tv/popular")
    @JvmSuppressWildcards
    suspend fun getTvSeriesList(@QueryMap options: Map<String, Any>): TvSeriesListResponse


    @GET("tv/{series_id}")
    suspend fun getTvSeriesDetails(@Path("series_id") seriesId: Int): TvSeriesDetails


}