package com.findmore.reelraves.ui.tvseries


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.findmore.reelraves.data.model.TvSeries
import com.findmore.reelraves.network.local.ApiConfig.ImageBaseUrl
import com.findmore.reelraves.ui.utils.LoadImage

@Composable
fun TvCard(
    onClick: () -> Unit,
    movie: TvSeries,
    modifier: Modifier = Modifier,
    isSelected: Boolean
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .border(
                width = if (isSelected) 3.dp else 1.dp,
                color = if (isSelected) Color.White else Color.Transparent, // Changed to Color.Red for visibility
                shape = RoundedCornerShape(8.dp)
            )
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            movie.poster_path?.let {
                val imageUrl = ImageBaseUrl + it
                LoadImage(
                    url = imageUrl,
                    cacheName = "movie_image_${movie.id}_$movie.poster_path"
                )
            }
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .background(Color.Black.copy(alpha = 0.6f))
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                movie.name?.let {
                    Text(
                        text = it,
                        textAlign = TextAlign.Start,
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.labelLarge
                    )
                }

                movie.vote_average?.let {
                    Text(
                        text = "Rating: $it",
                        textAlign = TextAlign.Start,
                        color = Color.White,
                        fontSize = 14.sp,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                movie.first_air_date?.let {
                    Text(
                        text = "Release Date: $it",
                        textAlign = TextAlign.Start,
                        color = Color.White,
                        fontSize = 14.sp,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                movie.original_language?.let {
                    Text(
                        text = "Language: ${it.uppercase()}",
                        textAlign = TextAlign.Start,
                        color = Color.White,
                        fontSize = 14.sp,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}