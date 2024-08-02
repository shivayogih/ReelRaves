package com.findmore.reelraves.data.repository

import com.findmore.reelraves.data.dao.MovieDao
import com.findmore.reelraves.data.dao.TvSeriesDao
import com.findmore.reelraves.data.model.Movie
import com.findmore.reelraves.data.model.TvSeries
import com.findmore.reelraves.network.RetrofitService
import javax.inject.Inject



class FavoritesRepository @Inject constructor(
    private val movieDao: MovieDao,
    private val tvSeriesDao: TvSeriesDao,
    retrofitService: RetrofitService
) {

    suspend fun addFavoriteMovie(movie: Movie) = movieDao.insertFavoriteMovie(movie)
    suspend fun removeFavoriteMovie(movie: Movie) = movieDao.deleteFavoriteMovie(movie)
    suspend fun getFavoriteMovies() = movieDao.getAllFavoriteMovies()
    suspend fun getFavoriteMovieById(movieId: Int) = movieDao.getFavoriteMovieById(movieId)


    suspend fun addFavoriteTvSeries(tvSeries: TvSeries) =
        tvSeriesDao.insertFavoriteTvSeries(tvSeries)

    suspend fun removeFavoriteTvSeries(tvSeries: TvSeries) =
        tvSeriesDao.deleteFavoriteTvSeries(tvSeries)

    suspend fun getFavoriteTvSeries() = tvSeriesDao.getAllFavoriteTvSeries()
    suspend fun getFavoriteTvSeriesById(tvSeriesId: Int) =
        tvSeriesDao.getFavoriteTvSeriesById(tvSeriesId)
}