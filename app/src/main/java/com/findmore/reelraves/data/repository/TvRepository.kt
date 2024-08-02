package com.findmore.reelraves.data.repository

import com.findmore.reelraves.common.Resource
import com.findmore.reelraves.data.model.Genre
import com.findmore.reelraves.data.model.MovieDetailsResponse
import com.findmore.reelraves.data.model.MoviesList
import com.findmore.reelraves.data.model.TvSeriesDetails
import com.findmore.reelraves.data.model.TvSeriesListResponse


interface TvRepository {
    suspend fun getGenres(): List<Genre>
    suspend fun saveGenres(genres: List<Genre>)
    suspend fun getSelectedGenres(): List<Genre>
    suspend fun updateGenre(genre:  Genre)

    suspend fun getTvSeriesList(queryMap: MutableMap<String, Any>): Resource<TvSeriesListResponse>
    suspend fun getTvSeriesDetails(seriesId: Int): Resource<TvSeriesDetails>

}