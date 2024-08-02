package com.findmore.reelraves.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.findmore.reelraves.data.model.TvSeries

@Dao
interface TvSeriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteTvSeries(tvSeries: TvSeries)

    @Delete
    suspend fun deleteFavoriteTvSeries(tvSeries: TvSeries)

    @Query("SELECT * FROM favorite_tv_series")
    suspend fun getAllFavoriteTvSeries(): List<TvSeries>

    @Query("SELECT * FROM favorite_tv_series WHERE id = :tvSeriesId")
    suspend fun getFavoriteTvSeriesById(tvSeriesId: Int): TvSeries?
}