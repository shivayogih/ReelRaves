package com.findmore.reelraves.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.findmore.reelraves.data.model.Genre
import com.findmore.reelraves.data.model.Movie
import com.findmore.reelraves.data.model.MovieGenres
import com.findmore.reelraves.data.model.TvSeries
import com.findmore.reelraves.data.repository.FavoritesRepository
import com.findmore.reelraves.data.repository.TvRepository
import com.findmore.reelraves.data.repository.MovieRepository
import com.findmore.reelraves.data.repository.PreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: PreferencesRepository,
    private val tvRepository: TvRepository,
    private val movieRepository: MovieRepository,
    private val favoritesRepository: FavoritesRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {


    private val _selectedGenres = MutableStateFlow<Set<Genre>>(emptySet())
    val selectedGenres: StateFlow<Set<Genre>> get() = _selectedGenres

    private val _selectedMovies = MutableStateFlow<Set<MovieGenres>>(emptySet())
    val selectedMovies: StateFlow<Set<MovieGenres>> get() = _selectedMovies


    var showWelcomeScreen by mutableStateOf(repository.isFirstLaunch())
        private set

    fun onWelcomeScreenShown() {
        repository.setFirstLaunch(false)
        showWelcomeScreen = false
    }


    val favoriteMovies = MutableLiveData<List<Movie>>()
    val favoriteTvSeries = MutableLiveData<List<TvSeries>>()

    init {
        loadFavorites()
    }

    private fun loadFavorites() {
        viewModelScope.launch {
            favoriteMovies.value = favoritesRepository.getFavoriteMovies()
            favoriteTvSeries.value = favoritesRepository.getFavoriteTvSeries()
        }
    }

    fun addFavoriteMovie(movie: Movie) {
        viewModelScope.launch {
            favoritesRepository.addFavoriteMovie(movie)
            loadFavorites()
        }
    }

    fun removeFavoriteMovie(movie: Movie) {
        viewModelScope.launch {
            favoritesRepository.removeFavoriteMovie(movie)
            loadFavorites()
        }
    }

    fun addFavoriteTvSeries(tvSeries: TvSeries) {
        viewModelScope.launch {
            favoritesRepository.addFavoriteTvSeries(tvSeries)
            loadFavorites()
        }
    }

    fun removeFavoriteTvSeries(tvSeries: TvSeries) {
        viewModelScope.launch {
            favoritesRepository.removeFavoriteTvSeries(tvSeries)
            loadFavorites()
        }
    }

}