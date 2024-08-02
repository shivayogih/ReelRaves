package com.findmore.reelraves.ui.tvseries

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.findmore.reelraves.viewmodel.GenreViewModel

@Composable
fun TvSeriesListScreen(onNavigateToDetail: (Int) -> Unit) {

    val context = LocalContext.current
    val viewModelTv: GenreViewModel = hiltViewModel()
    val tvSeriesList by viewModelTv.tvSeriesList.collectAsState()
    val listState = rememberLazyGridState()

    LaunchedEffect(Unit) {
        viewModelTv.fetchTvSeriesList()
    }

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull() }
            .collect { lastVisibleItem ->
                if (lastVisibleItem != null && lastVisibleItem.index == tvSeriesList.size - 1) {
                    viewModelTv.fetchTvSeriesList()
                }
            }
    }

    LaunchedEffect(viewModelTv.loadError.value) {
        if (viewModelTv.loadError.value?.isNotEmpty() == true) {
            Toast.makeText(context, viewModelTv.loadError.value, Toast.LENGTH_SHORT).show()
        }
    }


    Box(modifier = Modifier.fillMaxSize()) {
        val aspectRatio = 1.3f
        val columnCount = 4
        val gridWidth = LocalConfiguration.current.screenWidthDp.dp
        val itemWidth = (gridWidth / columnCount)

        var selectedMovieId by remember { mutableStateOf<Int?>(null) }
        LazyVerticalGrid(
            columns = GridCells.Fixed(columnCount),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            state = listState,
        ) {
            items(tvSeriesList) { tvShow ->
                TvCard(
                    onClick = {
                        selectedMovieId = tvShow.id
                        tvShow.id?.let { onNavigateToDetail(it) }
                    },
                    tvShow,
                    modifier = Modifier
                        .width(itemWidth)
                        .height(itemWidth * aspectRatio),
                    isSelected = selectedMovieId == tvShow.id,
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