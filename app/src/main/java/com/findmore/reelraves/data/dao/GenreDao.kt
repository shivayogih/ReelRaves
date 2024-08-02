package com.findmore.reelraves.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.findmore.reelraves.data.model.Genre


@Dao
interface GenreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(genres: List<Genre>)

    @Query("SELECT * FROM genre")
    suspend fun getAll(): List<Genre>

    @Update
    suspend fun updateGenre(genre:  Genre)

    @Query("SELECT * FROM genre WHERE selected = 1")
    fun getSelectedGenres(): List<Genre>

    @Query("UPDATE genre SET selected = :selected WHERE id IN (:genreIds)")
    suspend fun updateSelectedGenres(genreIds: List<Int>, selected: Boolean)
}

