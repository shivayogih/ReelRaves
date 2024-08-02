package com.findmore.reelraves.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.findmore.reelraves.ui.movies.MovieDetailScreen
import com.findmore.reelraves.ui.home.HomeScreen
import com.findmore.reelraves.ui.summary.SummaryScreen
import com.findmore.reelraves.ui.genres.GenreScreen
import com.findmore.reelraves.ui.movies.MovieGenresScreen
import com.findmore.reelraves.ui.settings.SettingsScreen
import com.findmore.reelraves.ui.tvseries.TvSeriesDetailScreen
import com.findmore.reelraves.ui.utils.Screens
import com.findmore.reelraves.ui.welcome.WelcomeScreen
import com.findmore.reelraves.viewmodel.MainViewModel


@Composable
fun NavigationGraph() {


    val viewModel: MainViewModel = hiltViewModel()
    val navController = rememberNavController()
    val startDestination =
        remember { if (viewModel.showWelcomeScreen) Screens.WelCome.route else Screens.Home.route }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screens.WelCome.route) {
            WelcomeScreen(
                onSkip = {
                    viewModel.onWelcomeScreenShown()
                    navController.navigate(Screens.Home.route) {
                        popUpTo(Screens.WelCome.route) { inclusive = true }
                    }
                },
                onNavigate = { destination ->
                    viewModel.onWelcomeScreenShown()
                    navController.navigate(destination) {
                        popUpTo(Screens.Home.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screens.Home.route) {
            HomeScreen(
                onNavigate = { destination ->
                    navController.navigate(destination) {
                        popUpTo(Screens.Home.route) { inclusive = true }
                    }
                },
                onActionClick = { destination ->
                    navController.navigate(destination) {
                        popUpTo(Screens.Home.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screens.Settings.route) {
            SettingsScreen(
                onNavigateBack = { destination ->
                    navController.navigate(destination)
                }
            )
        }

        composable(Screens.SelectGenres.route) {
            GenreScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(Screens.SelectMovies.route) {
            MovieGenresScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(Screens.ViewSelections.route) {
            SummaryScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable("${Screens.MovieDetails.route}/{movieId}") { backStackEntry ->
            MovieDetailScreen(
                onNavigateBack = { navController.popBackStack() },
                backStackEntry.arguments?.getString("movieId") ?: ""
            )
        }

        composable("${Screens.TvSeriesDetails.route}/{movieId}") { backStackEntry ->
            TvSeriesDetailScreen(
                onNavigateBack = { navController.popBackStack() },
                backStackEntry.arguments?.getString("movieId") ?: ""
            )
        }
    }
}


