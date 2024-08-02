package com.findmore.reelraves.ui.summary

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.findmore.reelraves.viewmodel.SummaryViewModel
import androidx.lifecycle.viewmodel.compose.viewModel



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SummaryScreen(onNavigateBack: (String) -> Unit) {

    val summaryViewModel: SummaryViewModel = hiltViewModel()

    val selectedGenres by summaryViewModel.selectedGenres.collectAsState()
    val selectedMovies by summaryViewModel.selectedMovies.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Summary") })
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Text("Favorite Genres:", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))
            selectedGenres.forEach { genre ->
                Text(genre.name)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text("Favorite Movies:", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))
            selectedMovies.forEach { movie ->
                Text(movie.title)
            }
        }
    }
}