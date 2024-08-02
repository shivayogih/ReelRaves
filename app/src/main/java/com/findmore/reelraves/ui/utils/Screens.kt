package com.findmore.reelraves.ui.utils

sealed class Screens(val route: String) {
    object WelCome : Screens("welcome_screen")
    object Home : Screens("home")
    object SelectGenres : Screens("select_genres")
    object SelectMovies : Screens("select_movies")
    object ViewSelections : Screens("view_selections")
    object MovieDetails : Screens("movie_details")
    object TvSeriesDetails : Screens("tv_series_details")
    object Settings : Screens("Settings")
}