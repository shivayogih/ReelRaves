package com.findmore.reelraves.ui.genres

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.findmore.reelraves.viewmodel.GenreViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenreScreen(onNavigateBack: (String) -> Unit) {

    val genreViewModel: GenreViewModel = hiltViewModel()
    val genres by genreViewModel.genres.collectAsState(emptyList())
    val selectedGenres by genreViewModel.selectedGenres.collectAsState(emptySet())

    /* LaunchedEffect(Unit) {
         genreViewModel.fetchGenres()
     }*/

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    "Select Genres",
                    color = Color.Black, style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                )
            })
        }
    ) { padding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(5),
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(genres) { genre ->
                GenreItem(genre, genre in selectedGenres, genreViewModel::toggleGenre)
            }
        }
    }


}

