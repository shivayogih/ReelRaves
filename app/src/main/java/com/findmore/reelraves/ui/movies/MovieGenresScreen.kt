package com.findmore.reelraves.ui.movies

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.findmore.reelraves.viewmodel.MovieViewModel
import com.findmore.reelraves.R
import com.findmore.reelraves.data.model.MovieGenres


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieGenresScreen(onNavigateBack: (String) -> Unit) {

    val movieViewModel: MovieViewModel = hiltViewModel()

    val movies by movieViewModel.movies.collectAsState(emptyList())
    val selectedMovies by movieViewModel.selectedMovies.collectAsState(emptySet())

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Select Movies") })
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(movies) { movie ->
                MovieItem(movie, selectedMovies, movieViewModel::toggleMovie)
            }
        }
    }
}


@Composable
fun MovieItem(movieGenres: MovieGenres, selectedMovieGenres: Set<MovieGenres>, onToggle: (MovieGenres) -> Unit) {
    val isSelected = movieGenres in selectedMovieGenres
    ListItem(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onToggle(movieGenres) },
        headlineContent = { Text(movieGenres.title) },
        trailingContent = {
            Icon(
                painter = if (isSelected) painterResource(id = R.drawable.ic_checked) else painterResource(
                    id = R.drawable.ic_unchecked
                ),
                contentDescription = "check_box",
                tint = if (isSelected) Color.Green else Color.Gray
            )
        }
    )
}