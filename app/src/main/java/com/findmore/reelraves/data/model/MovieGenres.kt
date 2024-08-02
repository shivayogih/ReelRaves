package com.findmore.reelraves.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "movie_genres")
data class MovieGenres(
    @PrimaryKey val id: Int, // Changed to Int to match the selectedMovies Set type
    val title: String,
    val watched: Boolean = false,
    var selected: Boolean = false
)