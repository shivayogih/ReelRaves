package com.findmore.reelraves.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.darkColorScheme
import androidx.tv.material3.lightColorScheme

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun ReelRavesTheme(
    isInDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
   /* val colorScheme = if (isInDarkTheme) {
        darkColorScheme(
            primary = Purple80,
            secondary = PurpleGrey80,
            tertiary = Pink80
        )
    } else {
        lightColorScheme(
            primary = Purple40,
            secondary = PurpleGrey40,
            tertiary = Pink40
        )
    }*/

    val colorScheme = if (isInDarkTheme) {
        BlackAndWhiteDarkColors
    } else {
        BlackAndWhiteLightColors
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}


// Define the colors for black and white theme
private val BlackAndWhiteDarkColors = darkColorScheme(
    primary = Color.White,
    onPrimary = Color.Black,
    secondary = Color.Gray,
    onSecondary = Color.White,
    background = Color.Black,
    onBackground = Color.White,
    surface = Color.DarkGray,
    onSurface = Color.White
)

private val BlackAndWhiteLightColors = lightColorScheme(
    primary = Color.Black,
    onPrimary = Color.White,
    secondary = Color.DarkGray,
    onSecondary = Color.Black,
    background =surface_color,
    onBackground = Color.Black,
    surface = surface_color,
    onSurface = Color.Black
)