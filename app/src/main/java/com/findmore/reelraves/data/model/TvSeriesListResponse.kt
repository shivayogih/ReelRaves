package com.findmore.reelraves.data.model

data class TvSeriesListResponse(
    val page: Int,
    val results: List<TvSeries>,
    val total_pages: Int,
    val total_results: Int
)