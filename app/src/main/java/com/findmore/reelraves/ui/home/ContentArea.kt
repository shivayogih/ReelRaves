package com.findmore.reelraves.ui.home


import androidx.compose.runtime.Composable

import androidx.compose.runtime.rememberCoroutineScope

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.tv.material3.Text
import com.findmore.reelraves.ui.movies.MovieListScreen
import com.findmore.reelraves.ui.tvseries.TvSeriesListScreen
import com.findmore.reelraves.ui.utils.MenuList.Companion.menuFavorite
import com.findmore.reelraves.ui.utils.MenuList.Companion.menuMovies
import com.findmore.reelraves.ui.utils.MenuList.Companion.menuTVShows
import com.findmore.reelraves.viewmodel.GenreViewModel
import com.findmore.reelraves.viewmodel.MainViewModel

@Composable
fun ContentArea(selectedMenuItem: String, onNavigateToDetail: (Int) -> Unit) {

    when (selectedMenuItem) {
        menuFavorite -> {
            // Your content for favorite menu item
            Text(text = "Favorite Content")
        }

        menuMovies -> {
            MovieListScreen(onNavigateToDetail = {
                onNavigateToDetail(it)
            })
        }
        menuTVShows -> {
            TvSeriesListScreen(onNavigateToDetail = {
                onNavigateToDetail(it)
            })
            // Your content for TV shows menu item
            Text(text = "TV Shows Content")
        }
    }
}