package com.findmore.reelraves.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Text
import com.findmore.reelraves.ui.theme.light_Gray
import com.findmore.reelraves.ui.theme.surface_color
import com.findmore.reelraves.ui.utils.MenuList

@Composable
fun MenuHome(selectedItem: String, onSelect: (String) -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(surface_color)
    ) {
        MenuList.menuItems.forEach { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
                shape = RectangleShape,
                elevation = CardDefaults.cardElevation(1.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (item == selectedItem) light_Gray else Color.Transparent,
                )
            ) {
                Text(
                    text = item,
                    textAlign = TextAlign.Start,
                    color = if (item == selectedItem) surface_color else light_Gray,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 16.dp)
                        .align(Alignment.CenterHorizontally)
                        .clickable { onSelect(item) },
                    style = androidx.compose.material3.MaterialTheme.typography.labelLarge
                )
            }

        }
    }
}