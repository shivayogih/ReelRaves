package com.findmore.reelraves.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.findmore.reelraves.ui.theme.surface_color
import com.findmore.reelraves.ui.utils.MenuList.Companion.menuFavorite
import com.findmore.reelraves.ui.utils.MenuList.Companion.menuMovies
import com.findmore.reelraves.ui.utils.MenuList.Companion.menuTVShows
import com.findmore.reelraves.ui.utils.Screens


@Composable
fun HomeScreen(onNavigate: (String) -> Unit, onActionClick: (String) -> Unit) {

    val selectedMenuItem = rememberSaveable  { mutableStateOf(menuFavorite) }

    Scaffold(
        topBar = {
            TopBarView(onActionClick = onActionClick)
        },
        containerColor = surface_color,
        content = { paddingValues ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                        .background(surface_color)
                ) {
                    MenuHome(
                        selectedItem = selectedMenuItem.value,
                        onSelect = { selectedMenuItem.value = it }
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(4f)
                ) {
                    ContentArea(selectedMenuItem.value, onNavigateToDetail = { movieId ->
                        when (selectedMenuItem.value) {
                            menuMovies -> {
                                onNavigate(Screens.MovieDetails.route + "/$movieId")
                            }
                            menuTVShows -> {//onNavigate(Screens.TvSeriesDetails.route+"/$movieId")
                                onNavigate(Screens.TvSeriesDetails.route + "/$movieId")
                            }
                            menuFavorite -> { //onNavigate(Screens.TvSeriesDetails.route+"/$movieId")
                            }
                        }
                    })
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarView(onActionClick: (String) -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = "ReelRaves",
                color = Color.White,
                style = MaterialTheme.typography.titleMedium
            )
        },
        actions = {
            IconButton(onClick = { onActionClick(Screens.Settings.route) }) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings",
                    modifier = Modifier.size(38.dp),
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Black,
            titleContentColor = Color.White,
            actionIconContentColor = Color.White
        )
    )
}