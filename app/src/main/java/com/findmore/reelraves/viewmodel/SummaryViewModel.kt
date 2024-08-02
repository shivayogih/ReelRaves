package com.findmore.reelraves.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.findmore.reelraves.data.model.Genre
import com.findmore.reelraves.data.model.MovieGenres
import com.findmore.reelraves.data.repository.TvRepository
import com.findmore.reelraves.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SummaryViewModel @Inject constructor(
    private val tvRepository: TvRepository,
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _selectedGenres = MutableStateFlow<Set<Genre>>(emptySet())
    val selectedGenres: StateFlow<Set<Genre>> get() = _selectedGenres

    private val _selectedMovies = MutableStateFlow<Set<MovieGenres>>(emptySet())
    val selectedMovies: StateFlow<Set<MovieGenres>> get() = _selectedMovies

    /*init {
        viewModelScope.launch {
            _selectedGenres.value = tvRepository.getSelectedGenres().toSet()
            _selectedMovies.value = movieRepository.getSelectedMovies().toSet()
        }

    }*/
}