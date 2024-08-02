package com.findmore.reelraves.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Typography

import com.findmore.reelraves.R


val Figtree = FontFamily(
    Font(R.font.figtree_light, FontWeight.Light),
    Font(R.font.figtree_regular, FontWeight.Normal),
    Font(R.font.figtree_medium, FontWeight.Medium),
    Font(R.font.figtree_bold, FontWeight.Bold),
)


val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = Figtree,
        fontSize = 24.sp,
        fontWeight = FontWeight.ExtraBold,
        color = DarkGray
    ),
    titleMedium = TextStyle(
        fontFamily = Figtree,
        fontSize = 18.sp,
        color = DarkGray
    ),
    titleSmall = TextStyle(
        fontFamily = Figtree,
        fontSize = 16.sp,
        color = DarkGray
    ),
    labelLarge = TextStyle(
        fontFamily = Figtree,
        fontSize = 18.sp,
        color = DarkGray
    ),
    labelMedium = TextStyle(
        fontFamily = Figtree,
        fontSize = 16.sp,
        color = DarkGray
    ),

    labelSmall = TextStyle(
        fontFamily = Figtree,
        fontSize = 14.sp,
        color = DarkGray
    ),

    bodyLarge = TextStyle(
        fontFamily = Figtree,
        fontSize = 18.sp,
        color = DarkGray
    ),
    bodyMedium = TextStyle(
        fontFamily = Figtree,
        fontSize = 16.sp,
        color = DarkGray
    ),
    bodySmall = TextStyle(
        fontFamily = Figtree,
        fontSize = 14.sp,
        color = DarkGray
    ),
    displayLarge = TextStyle(
        fontFamily = Figtree,
        fontSize = 18.sp,
        color = DarkGray
    ),
    displayMedium = TextStyle(
        fontFamily = Figtree,
        fontSize = 16.sp,
        color = DarkGray
    ),
    displaySmall = TextStyle(
        fontFamily = Figtree,
        fontSize = 12.sp,
        color = DarkGray
    ),
)