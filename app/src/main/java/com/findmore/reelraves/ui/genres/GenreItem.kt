package com.findmore.reelraves.ui.genres


import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.res.painterResource
import com.findmore.reelraves.R
import com.findmore.reelraves.data.model.Genre
import com.findmore.reelraves.ui.theme.color_Blue
import com.findmore.reelraves.ui.theme.light_Gray


@Composable
fun GenreItem(genre: Genre, isSelected: Boolean, onToggle: (Genre) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onToggle(genre) }
            .border(
                width = if (isSelected) 2.dp else 1.dp,
                color = if (isSelected) color_Blue else light_Gray,
                shape = RoundedCornerShape(8.dp)
            ),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = genre.name,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )
            )
            if (isSelected) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_checked),
                    contentDescription = "Checked",
                    tint = color_Blue,
                    modifier = Modifier.align(Alignment.TopEnd)
                )
            }
        }
    }
}