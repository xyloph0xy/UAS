package com.luluksofiyah.uas.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luluksofiyah.uas.data.Resource
import com.luluksofiyah.uas.data.model.Movie
import com.luluksofiyah.uas.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {

    private val _popularMoviesState = MutableStateFlow<Resource<List<Movie>>>(Resource.Loading())
    val popularMoviesState: StateFlow<Resource<List<Movie>>> get() = _popularMoviesState

    init {
        fetchPopularMovies()
    }

    private fun fetchPopularMovies() = viewModelScope.launch {
        movieRepository.getPopularMovies().collect { result ->
            _popularMoviesState.value = result
        }
    }
}