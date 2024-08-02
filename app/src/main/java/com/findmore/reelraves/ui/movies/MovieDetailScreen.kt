package com.findmore.reelraves.ui.movies

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

import com.findmore.reelraves.network.local.ApiConfig.ImageBaseUrl
import com.findmore.reelraves.ui.utils.LoadImage
import com.findmore.reelraves.viewmodel.MovieViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MovieDetailScreen(onNavigateBack: (String) -> Unit, movieId: String) {


    val movieViewModel: MovieViewModel = hiltViewModel()
    val movie by movieViewModel.movieDetails.collectAsState()

    LaunchedEffect(movieId) {
        movieViewModel.fetchMovieDetails(movieId.toInt())
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Backdrop Image with Loading and Error Handling
        movie?.backdrop_path?.let {
            val imageUrl = ImageBaseUrl + it
            LoadImage(
                url = imageUrl,
                cacheName = "movie_image_${movie?.id}_$movie.poster_path"
            )
        }

        // Movie Details
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
                .background(Color.Black.copy(alpha = 0.6f))
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.Top,  // Align items at the top
                modifier = Modifier.fillMaxWidth()
            ) {
                movie?.title?.let {
                    Text(
                        text = it,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .weight(4f)
                            .align(Alignment.Top)  // Align text at the top
                    )
                }

                var isFavorite by remember { mutableStateOf(false) }
                IconButton(
                    onClick = { isFavorite = !isFavorite },
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.Top)  // Align icon at the top
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = null,
                        tint = if (isFavorite) Color.Red else Color.White
                    )
                }
            }

            movie?.tagline?.let {
                Text(
                    text = it,
                    color = Color.Gray,
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Favorite Button


                // Play Button
                Button(
                    onClick = { /* Handle play action */ },
                    enabled = movie?.video == true,
                ) {
                    Text(text = "Play")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Genres
            FlowRow() {
                movie?.genres?.forEach { genre ->
                    Surface(
                        modifier = Modifier
                            .padding(4.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.Gray.copy(alpha = 0.8f)),
                        color = Color.Transparent // Set Surface color to transparent to only use background color
                    ) {
                        Text(
                            text = genre.name,
                            color = Color.White,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Overview
            movie?.overview?.let {
                Text(
                    text = it,
                    color = Color.White,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Additional Details
            Text(
                text = "Release Date: ${movie?.release_date}",
                color = Color.White,
                fontSize = 14.sp
            )
            Text(
                text = "Runtime: ${movie?.runtime ?: "N/A"} min",
                color = Color.White,
                fontSize = 14.sp
            )
            Text(
                text = "Language: ${movie?.original_language?.uppercase()}",
                color = Color.White,
                fontSize = 14.sp
            )
        }
    }
}


