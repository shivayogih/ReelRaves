package com.findmore.reelraves.viewmodel

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.findmore.reelraves.common.Resource
import com.findmore.reelraves.data.model.Genre
import com.findmore.reelraves.data.model.TvSeries
import com.findmore.reelraves.data.model.TvSeriesDetails
import com.findmore.reelraves.data.model.TvSeriesListResponse
import com.findmore.reelraves.data.repository.TvRepository
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
class GenreViewModel @Inject constructor(
    private val tvRepository: TvRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _genres = MutableStateFlow<List<Genre>>(emptyList())
    val genres: StateFlow<List<Genre>> get() = _genres

    private val _selectedGenres = MutableStateFlow<Set<Genre>>(emptySet())
    val selectedGenres: StateFlow<Set<Genre>> get() = _selectedGenres
    val selectedCount = MutableStateFlow(0)


    init {
        viewModelScope.launch {
            fetchGenres()
        }
    }

    suspend fun fetchGenres() {
        val genres = tvRepository.getGenres()
        _genres.value = genres

        // Save genres to the database
        viewModelScope.launch {
            tvRepository.saveGenres(genres)
        }
    }


    fun toggleGenre(genre: Genre) {
        viewModelScope.launch {
            val currentCount = selectedCount.value
            val updatedSet = _selectedGenres.value.toMutableSet()
            if (updatedSet.contains(genre)) {
                genre.selected = false
                selectedCount.value = currentCount - 1
                updatedSet.remove(genre)
            } else {
                genre.selected = true
                updatedSet.add(genre)
            }
            _selectedGenres.value = updatedSet
            tvRepository.updateGenre(genre)
        }
    }


    //====================================================================
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    // Tv Series List
    private val _tvSeriesList = MutableStateFlow<List<TvSeries>>(emptyList())
    val tvSeriesList: StateFlow<List<TvSeries>> get() = _tvSeriesList

    // Tv Series Details
    private val _tvSeriesDetails = MutableStateFlow<TvSeriesDetails?>(null)
    val tvSeriesDetails: StateFlow<TvSeriesDetails?> get() = _tvSeriesDetails

    // Movie List Pagination Count
    private var currentPage = 1

    //=====================================================================
    fun fetchTvSeriesList() {
        viewModelScope.launch {
            if (!NetworkUtils.isNetworkAvailable(context)) {
                loadError.value = "No network connection or internet access"
                return@launch
            }
            isLoading.value = true
            try {

                val genreIds = selectedGenres.value.map { it.id }.joinToString(separator = ",")
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
                    tvRepository.getTvSeriesList(queryMap)
                }

                handleResponse(response)
            } catch (e: Exception) {
                loadError.value = "An unexpected error occurred: ${e.localizedMessage}"
                isLoading.value = false
            }
        }
    }

    private fun handleResponse(response: Resource<TvSeriesListResponse>) {
        when (response) {
            is Resource.Success -> {
                loadError.value = ""
                isLoading.value = false
                response.data?.results?.let {
                    _tvSeriesList.value += it
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


    fun fetchTvSeriesDetails(tvSeriesId: Int) {
        viewModelScope.launch {
            if (!NetworkUtils.isNetworkAvailable(context)) {
                loadError.value = "No network connection or internet access"
                return@launch
            }
            isLoading.value = true
            try {
                val response = withContext(Dispatchers.IO) {
                    tvRepository.getTvSeriesDetails(tvSeriesId)
                }
                handleTvSeriesDetailsResponse(response)

            } catch (e: Exception) {
                loadError.value = "An unexpected error occurred: ${e.localizedMessage}"
                isLoading.value = false
            }
        }
    }

    private fun handleTvSeriesDetailsResponse(response: Resource<TvSeriesDetails>) {
        when (response) {

            is Resource.Success -> {
                loadError.value = ""
                isLoading.value = false
                response.data?.let {
                    _tvSeriesDetails.value = it
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