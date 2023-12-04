package com.zaher.cleanwithmvvm.presentation.viewmodel

import com.zaher.cleanwithmvvm.domain.model.Movie

data class MovieUiState(
    val moviesList: List<Movie> = emptyList(),
    val isLoading: Boolean = false
)