package com.findmore.reelraves.ui.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.findmore.reelraves.R

@Composable
fun LoadImageWithLoadingIndicator(url: String, cacheName: String) {
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .diskCacheKey(cacheName)
            .networkCachePolicy(CachePolicy.ENABLED)
            .diskCachePolicy(CachePolicy.DISABLED)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .build()
    )

    val state = painter.state
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        when (state) {
            is AsyncImagePainter.State.Loading -> {
                CircularProgressIndicator(color = Color.Blue)
            }

            is AsyncImagePainter.State.Success -> {
                /* AsyncImage(
                     painter = painter,
                     contentDescription = null,
                     modifier = Modifier.fillMaxSize()
                 )*/

                AsyncImage(
                    model = painter,
                    contentDescription = "ImageRequest example",
                    modifier = Modifier.fillMaxSize()
                )
            }

            is AsyncImagePainter.State.Error -> {
                // Handle error state here, e.g., show a default or error image
                Image(
                    painter = painterResource(R.drawable.ic_default_image),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
            }

            else -> {
                // Handle any other states if necessary
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadImageWithLoadingIndicatorPreview() {
    LoadImageWithLoadingIndicator("https://image.tmdb.org/t/p/w500/1E5baAaEse26fej7uHcjOgEE2t2.jpg", "abc")
}