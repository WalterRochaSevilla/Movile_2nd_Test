package com.calyrsoft.ucbp1.features.movie.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.calyrsoft.ucbp1.features.movie.domain.model.MovieModel
import com.calyrsoft.ucbp1.features.movie.domain.usecase.FetchPopularMoviesUseCase
import com.calyrsoft.ucbp1.features.movie.domain.usecase.UpdateMovieLikeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PopularMoviesViewModel(
    private val fetchPopularMovies: FetchPopularMoviesUseCase,
    private val updateMovieLike: UpdateMovieLikeUseCase
): ViewModel() {

    sealed class UiState {
        object Loading : UiState()
        data class Success(val movies: List<MovieModel>) : UiState()
        data class Error(val message: String) : UiState()
    }

    private val _state = MutableStateFlow<UiState>(UiState.Loading)
    val state: StateFlow<UiState> = _state.asStateFlow()

    fun fetchPopularMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = UiState.Loading
            val result = fetchPopularMovies.invoke()
            result.fold(
                onSuccess = { movies ->
                    _state.value = UiState.Success(movies)
                },
                onFailure = { exception ->
                    _state.value = UiState.Error(exception.message ?: "Error desconocido")
                }
            )
        }
    }

    fun toggleMovieLike(movieId: Int, currentIsLiked: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                updateMovieLike.invoke(movieId, !currentIsLiked)
                fetchPopularMovies()
            } catch (e: Exception) {
                _state.value = UiState.Error("Error al actualizar like: ${e.message}")
            }
        }
    }
}