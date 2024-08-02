package com.findmore.reelraves.ui.movies

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.findmore.reelraves.viewmodel.MovieViewModel
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.findmore.reelraves.ui.genres.GenreItem


@Composable
fun MovieListScreen(onNavigateToDetail: (Int) -> Unit) {

    val context = LocalContext.current
    val viewModelMovies: MovieViewModel = hiltViewModel()
    val moviesList by viewModelMovies.moviesList.collectAsState()
    val listState = rememberLazyGridState()

    LaunchedEffect(Unit) {
        viewModelMovies.fetchMoviesList()
    }

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull() }
            .collect { lastVisibleItem ->
                if (lastVisibleItem != null && lastVisibleItem.index == moviesList.size - 1) {
                    viewModelMovies.fetchMoviesList()
                }
            }
    }

    LaunchedEffect(viewModelMovies.loadError.value) {
        if (viewModelMovies.loadError.value?.isNotEmpty() == true) {
            Toast.makeText(context, viewModelMovies.loadError.value, Toast.LENGTH_SHORT).show()
        }
    }


    Box(modifier = Modifier.fillMaxSize()) {
        val aspectRatio = 1.3f
        val columnCount = 4
        val gridWidth = LocalConfiguration.current.screenWidthDp.dp
        val itemWidth = (gridWidth / columnCount)

        var selectedMovieId by rememberSaveable { mutableStateOf<Int?>(null) }

        LazyVerticalGrid(
            columns = GridCells.Fixed(columnCount),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            state = listState,
        ) {
            items(moviesList) { movie ->
                MovieCard(
                    onClick = {
                        selectedMovieId = movie.id
                        movie.id?.let { onNavigateToDetail(it) }
                    },
                    movie,
                    modifier = Modifier
                        .width(itemWidth)
                        .height(itemWidth * aspectRatio),
                    isSelected = selectedMovieId == movie.id,
                )
            }

            /*  if (viewModelMovies.isLoading.value) {
                  item {
                      CircularProgressIndicator(
                          modifier = Modifier
                              .size(30.dp)
                              .align(Alignment.BottomCenter)
                              .padding(16.dp)
                      )
                  }
              }*/
        }

    }
}