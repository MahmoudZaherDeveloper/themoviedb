package com.zaher.cleanwithmvvm.presentation.ui.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zaher.cleanwithmvvm.presentation.viewmodel.MoviesViewModel

@Composable
fun MovieListScreen(moviesViewModel: MoviesViewModel = hiltViewModel()) {

    val state = moviesViewModel.state.value

    LazyColumn(
        Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        items(state.moviesList.size) { i ->
            MovieItem(movie = state.moviesList[i], onItemClick = {})
        }
    }
}