package com.findmore.reelraves.data.model

import java.io.Serializable

data class MoviesList(
    var page: Int=0,
    var movies: List<Movie>?= listOf(),
    var total_pages: Int=0,
    var total_results: Int=0
): Serializable