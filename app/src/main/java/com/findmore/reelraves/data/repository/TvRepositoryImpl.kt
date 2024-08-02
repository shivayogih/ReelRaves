package com.findmore.reelraves.data.repository

import android.util.Log
import com.findmore.reelraves.common.Resource
import com.findmore.reelraves.data.dao.GenreDao
import com.findmore.reelraves.data.model.Genre
import com.findmore.reelraves.data.model.MovieDetailsResponse
import com.findmore.reelraves.data.model.MoviesList
import com.findmore.reelraves.data.model.TvSeriesDetails
import com.findmore.reelraves.data.model.TvSeriesListResponse
import com.findmore.reelraves.network.RetrofitService
import com.findmore.reelraves.network.local.ApiConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TvRepositoryImpl @Inject constructor(
    private val genreDao: GenreDao,
    private val retrofitService: RetrofitService
) : TvRepository {


    override suspend fun getSelectedGenres(): List<Genre> {
        /* return withContext(Dispatchers.IO) {
             genreDao.getAll()
         }*/
        val genres = withContext(Dispatchers.IO) {
            genreDao.getSelectedGenres()
        }

        Log.d("GenreRepository:", "Selected genres from DB: $genres")
        return genres
    }

    override suspend fun updateGenre(genre: Genre) {
        withContext(Dispatchers.IO) {
            genreDao.updateGenre(genre)
        }
    }


    override suspend fun getGenres(): List<Genre> {
        return withContext(Dispatchers.IO) {
            val response = retrofitService.getTVGenres(ApiConfig.TMDB_API_KEY)
            response.genres
        }
    }

    override suspend fun saveGenres(genres: List<Genre>) {
        withContext(Dispatchers.IO) {
            genreDao.insertAll(genres)
        }
    }


    override suspend fun getTvSeriesList(queryMap: MutableMap<String, Any>): Resource<TvSeriesListResponse> {
        try {
            return Resource.Success(retrofitService.getTvSeriesList(queryMap))
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred: ${e.localizedMessage}")
        }
    }

    override suspend fun getTvSeriesDetails(seriesId: Int): Resource<TvSeriesDetails> {
        try {
            return Resource.Success(
                retrofitService.getTvSeriesDetails(seriesId)
            )
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred: ${e.localizedMessage}")
        }
    }

}