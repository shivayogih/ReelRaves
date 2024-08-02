package com.findmore.reelraves.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favorite_movies")
data class Movie(
    @PrimaryKey
    val id: Int? = null,
    val adult: Boolean = false,
    val backdrop_path: String? = "",
    val genre_ids: List<Int> = listOf(),
    val original_language: String? = "",
    val original_title: String? = "",
    val overview: String? = "",
    val popularity: Double? = 0.0,
    val poster_path: String? = "",
    val release_date: String? = "",
    val title: String? = "",
    val video: Boolean = false,
    val vote_average: Double? = 0.0,
    val vote_count: Int? = 0
)