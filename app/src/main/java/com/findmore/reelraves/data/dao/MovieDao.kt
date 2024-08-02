package com.findmore.reelraves.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.findmore.reelraves.data.model.Movie
import com.findmore.reelraves.data.model.MovieGenres

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteMovie(movie: Movie)

    @Delete
    suspend fun deleteFavoriteMovie(movie: Movie)

    @Query("SELECT * FROM favorite_movies")
    suspend fun getAllFavoriteMovies(): List<Movie>

    @Query("SELECT * FROM favorite_movies WHERE id = :movieId")
    suspend fun getFavoriteMovieById(movieId: Int): Movie?


}