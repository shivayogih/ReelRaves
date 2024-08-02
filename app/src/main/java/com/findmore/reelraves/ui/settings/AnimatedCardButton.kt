package com.findmore.reelraves.ui.settings

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay


@Composable
fun AnimatedCardButton(
    text: String,
    image: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val visible = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(700) // Start delay for animations
        visible.value = true
    }

    AnimatedVisibility(
        visible = visible.value,
        enter = fadeIn(initialAlpha = 0.3f) + expandIn(expandFrom = Alignment.Center),
        exit = fadeOut() + shrinkOut(shrinkTowards = Alignment.Center)
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = modifier
                .size(180.dp)
                .padding(8.dp)
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp),
            onClick = onClick
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(6.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    painter = painterResource(image),
                    contentDescription = "type",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(48.dp)
                        .weight(1f)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = text,
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(6.dp, 6.dp)
                        .align(Alignment.CenterHorizontally)
                        .weight(1f),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}
