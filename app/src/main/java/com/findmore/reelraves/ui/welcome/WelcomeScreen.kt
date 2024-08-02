package com.findmore.reelraves.ui.welcome

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.findmore.reelraves.R
import com.findmore.reelraves.ui.settings.AnimatedCardButton
import com.findmore.reelraves.ui.utils.Screens
import kotlinx.coroutines.delay


@Composable
fun WelcomeScreen(onSkip: () -> Unit, onNavigate: (String) -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        OutlinedButton(
            onClick = onSkip,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        ) {
            Text("Skip", color = Color.White)
        }

        val visible = remember { mutableStateOf(true) }

        LaunchedEffect(Unit) {
            delay(500) // Start delay for animations
            visible.value = true
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {
            AnimatedVisibility(
                visible = visible.value,
                enter = fadeIn(initialAlpha = 0.3f) + expandIn(expandFrom = Alignment.Center),
                exit = fadeOut() + shrinkOut(shrinkTowards = Alignment.Center)
            ) {
                Text(
                    text = "Welcome to ReelRaves",
                    fontSize = 28.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 30.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                AnimatedCardButton(
                    text = "Select Favorite Genres",
                    R.drawable.ic_genre,
                    onClick = { onNavigate(Screens.SelectGenres.route) },
                )

                Spacer(modifier = Modifier.width(8.dp))

                AnimatedCardButton(
                    text = "Select Favorite Movies", R.drawable.ic_movies,
                    onClick = { onNavigate(Screens.SelectMovies.route) }

                )

                Spacer(modifier = Modifier.width(8.dp))

                AnimatedCardButton(
                    text = "View Selections", R.drawable.ic_favorite,
                    onClick = { onNavigate(Screens.ViewSelections.route) }
                )
            }
        }
    }
}
