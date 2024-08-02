package com.findmore.reelraves.ui.utils


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.CachePolicy


import coil.request.ImageRequest

@Composable
fun LoadImage(url: String, cacheName: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .diskCacheKey(cacheName)
            .networkCachePolicy(CachePolicy.ENABLED)
            .diskCachePolicy(CachePolicy.DISABLED)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .build(),
        modifier = Modifier.fillMaxSize(),
        contentDescription = cacheName,
        contentScale = ContentScale.Crop
    )
}

@Preview(showBackground = true)
@Composable
fun LoadImagePreview() {
    LoadImage("https://image.tmdb.org/t/p/w500/1E5baAaEse26fej7uHcjOgEE2t2.jpg", "abc")
}