package com.findmore.reelraves.viewmodel

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.findmore.reelraves.common.Resource
import com.findmore.reelraves.data.model.MovieGenres
import com.findmore.reelraves.data.model.MovieDetailsResponse
import com.findmore.reelraves.data.model.MoviesList
import com.findmore.reelraves.data.model.Movie
import com.findmore.reelraves.data.repository.MovieRepository
import com.findmore.reelraves.network.NetworkUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    // Movie List Pagination Count
    private var currentPage = 1

    private val _movies = MutableStateFlow<List<MovieGenres>>(emptyList())
    val movies: StateFlow<List<MovieGenres>> get() = _movies

    private val _selectedMovies = MutableStateFlow<Set<MovieGenres>>(emptySet())
    val selectedMovies: StateFlow<Set<MovieGenres>> get() = _selectedMovies

    // Movie List
    private val _moviesList = MutableStateFlow<List<Movie>>(emptyList())
    val moviesList: StateFlow<List<Movie>> get() = _moviesList

    // Movie Details
    private val _movieDetails = MutableStateFlow<MovieDetailsResponse?>(null)
    val movieDetails: StateFlow<MovieDetailsResponse?> get() = _movieDetails



    fun toggleMovie(movieGenres: MovieGenres) {

    }


    fun fetchMoviesList() {
        viewModelScope.launch {
            if (!NetworkUtils.isNetworkAvailable(context)) {
                loadError.value = "No network connection or internet access"
                return@launch
            }
            isLoading.value = true
            try {

                val genreIds = selectedMovies.value.map { it.id }.joinToString(separator = ",")
                val queryMap = mutableMapOf<String, Any>().apply {
                    put("include_adult", false)
                    put("include_video", true)
                    put("language", "en-US")
                    put("page", currentPage)
                    put("sort_by", "popularity.desc")
                    if (genreIds?.isNotEmpty() == true) {
                        put("with_genres", genreIds)
                    }
                }

                val response = withContext(Dispatchers.IO) {
                    movieRepository.getMoviesList(queryMap)
                }

                handleResponse(response)
            } catch (e: Exception) {
                loadError.value = "An unexpected error occurred: ${e.localizedMessage}"
                isLoading.value = false
            }
        }
    }

    private fun handleResponse(response: Resource<MoviesList>) {
        when (response) {
            is Resource.Success -> {
                loadError.value = ""
                isLoading.value = false
                response.data?.movies?.let {
                    _moviesList.value += it
                }
                currentPage++
            }

            is Resource.Error -> {
                loadError.value = response.message ?: "An unknown error occurred"
                isLoading.value = false
            }

            is Resource.Loading -> {
                isLoading.value = true
            }
        }
    }


    //======fetch Movie Details and Handle the Responses=====================


    fun fetchMovieDetails(movieId: Int) {
        viewModelScope.launch {
            if (!NetworkUtils.isNetworkAvailable(context)) {
                loadError.value = "No network connection or internet access"
                return@launch
            }
            isLoading.value = true
            try {
                val response = withContext(Dispatchers.IO) {
                    movieRepository.getMovieDetails(movieId)
                }
                handleMovieDetailsResponse(response)

            } catch (e: Exception) {
                loadError.value = "An unexpected error occurred: ${e.localizedMessage}"
                isLoading.value = false
            }
        }
    }

    private fun handleMovieDetailsResponse(response: Resource<MovieDetailsResponse>) {
        when (response) {

            is Resource.Success -> {
                loadError.value = ""
                isLoading.value = false
                response.data?.let {
                    _movieDetails.value = it
                }
            }

            is Resource.Error -> {
                loadError.value = response.message ?: "An unknown error occurred"
                isLoading.value = false
            }

            is Resource.Loading -> {
                isLoading.value = true
            }

        }
    }

    //===============================================

}

